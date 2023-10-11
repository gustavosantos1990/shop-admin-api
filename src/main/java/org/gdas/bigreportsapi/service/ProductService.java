package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Product;
import org.gdas.bigreportsapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
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

    public List<Product> findAll(boolean includeDeleted) {
        return includeDeleted ? productRepository.findAll() : productRepository.findByDeletedAtIsNull();
    }

    public Product findByID(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }

    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    public Product delete(UUID id) {
        Product product = findByID(id);
        product.setDeletedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return productRepository.save(product);
    }

    public Product update(UUID id, Product entity) {
        Product product = findByID(id);
        product.setPrice(entity.getPrice());
        product.setDescription(entity.getDescription());
        return productRepository.save(product);
    }
}
