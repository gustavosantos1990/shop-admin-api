package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.application.ports.out.CreateProductPort;
import com.gdas.shopadminapi.product.application.ports.out.FindAllProductsPort;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class ProductPersistenceAdapter implements
        FindAllProductsPort,
        CreateProductPort {

    private final ProductRepository productRepository;

    ProductPersistenceAdapter(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }
}
