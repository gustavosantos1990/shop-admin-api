package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.application.ports.out.CreateProductPort;
import com.gdas.shopadminapi.product.application.ports.out.FindAllProductsPort;
import com.gdas.shopadminapi.product.application.ports.out.FindProductByIdPort;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
class ProductPersistenceAdapter implements
        FindAllProductsPort,
        CreateProductPort,
        FindProductByIdPort {

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

    @Override
    public Optional<Product> find(UUID id) {
        return productRepository.findById(id);
    }
}
