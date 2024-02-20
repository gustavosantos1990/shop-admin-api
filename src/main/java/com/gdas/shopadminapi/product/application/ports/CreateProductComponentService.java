package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.CreateProductComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.out.CreateProductComponentPort;
import com.gdas.shopadminapi.product.application.ports.out.FindComponentByIdPort;
import com.gdas.shopadminapi.product.application.ports.out.FindProductByIdPort;
import com.gdas.shopadminapi.product.application.ports.out.FindProductComponentByIdPort;
import com.gdas.shopadminapi.product.domain.Component;
import com.gdas.shopadminapi.product.domain.Product;
import com.gdas.shopadminapi.product.domain.ProductComponent;
import com.gdas.shopadminapi.product.domain.ProductComponentId;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Service
class CreateProductComponentService implements CreateProductComponentUseCase {

    private final CreateProductComponentPort createProductComponentPort;
    private final FindProductComponentByIdPort findProductComponentByIdPort;
    private final FindProductByIdPort findProductByIdPort;
    private final FindComponentByIdPort findComponentByIdPort;

    CreateProductComponentService(CreateProductComponentPort createProductComponentPort, FindProductComponentByIdPort findProductComponentByIdPort, FindProductByIdPort findProductByIdPort, FindComponentByIdPort findComponentByIdPort) {
        this.createProductComponentPort = createProductComponentPort;
        this.findProductComponentByIdPort = findProductComponentByIdPort;
        this.findProductByIdPort = findProductByIdPort;
        this.findComponentByIdPort = findComponentByIdPort;
    }

    @Override
    public ProductComponent apply(ProductComponent productComponent) {
        loadAndFillId(productComponent);
        validateInput(productComponent);
        return createProductComponentPort.create(productComponent);
    }

    private void loadAndFillId(ProductComponent productComponent) {
        Optional<ProductComponent> optProductComponent = findProductComponentByIdPort.findById(productComponent.getProductComponentId());
        optProductComponent.ifPresent(pc -> {throw new ResponseStatusException(PRECONDITION_FAILED,
                format("component %s is already included to product %s",
                        pc.getProductComponentId().getComponent().getName(), pc.getProductComponentId().getProduct().getName()));});

        try {
            CompletableFuture<Optional<Product>> futureProduct =
                    CompletableFuture.supplyAsync(() -> findProductByIdPort.findById(productComponent.getProductComponentId().getProduct().getId()));
            CompletableFuture<Optional<Component>> futureComponent =
                    CompletableFuture.supplyAsync(() -> findComponentByIdPort.findById(productComponent.getProductComponentId().getComponent().getId()));
            CompletableFuture.allOf(futureProduct, futureComponent);

            Product product = futureProduct.get().orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                    format("Invalid product ID (%s)", productComponent.getProductComponentId().getProduct().getId())));
            Component component = futureComponent.get().orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                    format("Invalid component ID (%s)", productComponent.getProductComponentId().getComponent().getId())));

            productComponent.setProductComponentId(new ProductComponentId(product, component));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private void validateInput(ProductComponent productComponent) {
        if(productComponent.getProductComponentId().getComponent().getMeasure().isMultiDimension()) {
            if ((productComponent.getWidth() == null || productComponent.getWidth().compareTo(BigDecimal.ZERO) <= 0)
                    || (productComponent.getHeight() == null || productComponent.getHeight().compareTo(BigDecimal.ZERO) <= 0)) {
                throw new ResponseStatusException(PRECONDITION_FAILED, "must inform both height and width");
            }
            return;
        }

        if(productComponent.getAmount() == null || productComponent.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ResponseStatusException(PRECONDITION_FAILED, "must inform amount");
        }
    }
}
