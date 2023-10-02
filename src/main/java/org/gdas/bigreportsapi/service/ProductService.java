package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Component;
import org.gdas.bigreportsapi.model.entity.Product;
import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductComponentService productComponentService;
    private final ComponentService componentService;

    public ProductService(ProductRepository productRepository, ProductComponentService productComponentService, ComponentService componentService) {
        this.productRepository = productRepository;
        this.productComponentService = productComponentService;
        this.componentService = componentService;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findByID(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    public ProductComponent saveComponent(UUID id, ProductComponent entity) {
        Product product = findByID(id);
        Component component = componentService.findByID(entity.getComponent().getId());
        entity.setProduct(product);
        entity.setComponent(component);
        return productComponentService.save(entity);
    }

    public Product update(UUID id, Product product) {
        Product saved = findByID(id);

        if (saved.isReady()) updateForReadyProduct(saved, product);
        if (!saved.isReady() && product.isReady()) updateForNotReadyProduct(saved, product);

        return productRepository.save(saved);
    }

    private void updateForReadyProduct(Product existing, Product product) {
        existing.setName(product.getName());
    }

    private void updateForNotReadyProduct(Product existing, Product product) {
        validateReadiness(product);
        existing.setName(product.getName());
    }

    private void validateReadiness(Product product) {
        if (product.getComponents() == null || product.getComponents().isEmpty()) {
            throw new IllegalStateException("Produto sem componentes cadastrados");
        }
        product.setReady(true);
    }

}
