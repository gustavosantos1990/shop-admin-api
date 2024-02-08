package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.CreateProductUseCase;
import com.gdas.shopadminapi.product.application.ports.out.CreateProductPort;
import com.gdas.shopadminapi.product.domain.Product;
import com.gdas.shopadminapi.product.domain.enumeration.ProductStatus;
import org.springframework.stereotype.Service;

@Service
class CreateProductService implements CreateProductUseCase {

    private final CreateProductPort createProductPort;

    CreateProductService(CreateProductPort createProductPort) {
        this.createProductPort = createProductPort;
    }

    @Override
    public Product apply(Product product) {
        product.setStatus(ProductStatus.CREATED);
        return createProductPort.create(product);
    }

}
