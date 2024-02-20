package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.UpdateProductComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.out.FindProductComponentByIdPort;
import com.gdas.shopadminapi.product.application.ports.out.UpdateProductComponentPort;
import com.gdas.shopadminapi.product.domain.ProductComponent;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Service
class UpdateProductComponentService implements UpdateProductComponentUseCase {

    private final UpdateProductComponentPort updateProductComponentPort;
    private final FindProductComponentByIdPort findProductComponentByIdPort;

    UpdateProductComponentService(UpdateProductComponentPort updateProductComponentPort, FindProductComponentByIdPort findProductComponentByIdPort) {
        this.updateProductComponentPort = updateProductComponentPort;
        this.findProductComponentByIdPort = findProductComponentByIdPort;
    }

    @Override
    public ProductComponent apply(ProductComponent productComponent) {
        ProductComponent existingProductComponent = findProductComponentByIdPort.findById(productComponent.getProductComponentId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid product/component ids (%s/%s)",
                        productComponent.getProductComponentId().getProduct().getId(), productComponent.getProductComponentId().getComponent().getId())));
        validateInput(existingProductComponent, productComponent);
        copyProperties(existingProductComponent, productComponent);
        return updateProductComponentPort.update(productComponent);
    }

    private void validateInput(ProductComponent existingProductComponent, ProductComponent productComponent) {
        if(existingProductComponent.getProductComponentId().getComponent().getMeasure().isMultiDimension()) {
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

    private void copyProperties(ProductComponent existingProductComponent, ProductComponent productComponent) {
        if(existingProductComponent.getProductComponentId().getComponent().getMeasure().isMultiDimension()) {
            existingProductComponent.setHeight(productComponent.getHeight());
            existingProductComponent.setWidth(productComponent.getWidth());
            return;
        }

        existingProductComponent.setAmount(productComponent.getAmount());
    }

}
