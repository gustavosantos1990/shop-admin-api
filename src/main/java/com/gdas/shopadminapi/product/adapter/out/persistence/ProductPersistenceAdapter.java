package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.application.ports.out.FindAllProductsPort;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductPersistenceAdapter implements FindAllProductsPort {

    private final ProductRepository productRepository;

    ProductPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

}
