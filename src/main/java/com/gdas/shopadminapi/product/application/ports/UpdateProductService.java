package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.UpdateProductUseCase;
import com.gdas.shopadminapi.product.application.ports.out.CreateProductPort;
import com.gdas.shopadminapi.product.application.ports.out.FindProductByIdPort;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
class UpdateProductService implements UpdateProductUseCase {

    private static final String[] PROPERTIES_TO_IGNORE_ON_UPDATE =
            {"id", "name", "status", "createdAt", "deletedAt", "photoAddress"};

    private final CreateProductPort createProductPort;
    private final FindProductByIdPort findProductByIdPort;

    UpdateProductService(CreateProductPort createProductPort, FindProductByIdPort findProductByIdPort) {
        this.createProductPort = createProductPort;
        this.findProductByIdPort = findProductByIdPort;
    }

    @Override
    public Product apply(UUID id, Product product) {
        Product existingProduct = findProductByIdPort.find(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("invalid product id (%s)", id)));
        copyProperties(product, existingProduct, PROPERTIES_TO_IGNORE_ON_UPDATE);
        return createProductPort.create(existingProduct);
    }

}
