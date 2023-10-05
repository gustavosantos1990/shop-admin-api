package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Product;
import org.gdas.bigreportsapi.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ComponentService componentService;
    private final CustomerService customerService;

    public ProductService(
            ProductRepository productRepository,
            ComponentService componentService,
            CustomerService customerService) {
        this.productRepository = productRepository;
        this.componentService = componentService;
        this.customerService = customerService;
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product findByID(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }

    public Product save(Product entity) {
        return productRepository.save(entity);
    }

}
