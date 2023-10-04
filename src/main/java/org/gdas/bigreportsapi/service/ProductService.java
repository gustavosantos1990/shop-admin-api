package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.*;
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
    private final ProductComponentService productComponentService;
    private final ComponentService componentService;
    private final RevisionService revisionService;

    public ProductService(
            ProductRepository productRepository,
            ProductComponentService productComponentService,
            ComponentService componentService,
            RevisionService revisionService) {
        this.productRepository = productRepository;
        this.productComponentService = productComponentService;
        this.componentService = componentService;
        this.revisionService = revisionService;
    }

    public Page<Product> findAll(boolean ready, Pageable pageable) {
        return productRepository.findByReady(ready, pageable);
    }

    public Product findByID(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }
    public Product save(Product entity) {
        return productRepository.save(entity);
    }

    public ProductComponent saveComponent(UUID productID, Integer revisionNumber, ProductComponent entity) {
        Component component = componentService.findByID(entity.getProductComponentID().getComponent().getId());
        Revision revision = revisionService.findByProductIDAndRevisionNumber(productID, revisionNumber);

        entity.setProductComponentID(new ProductComponentID(revision, component));

        return productComponentService.save(entity);
    }

    public ProductComponent saveComponentForNewRevision(UUID productID, ProductComponent entity) {
        Integer revisionNumber = revisionService.findMaxRevisionNumberByProduct(productID);

        if (revisionNumber == null) {
            revisionService.save(new Revision());
        }

        return saveComponent(productID, revisionNumber, entity);
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
//        if (product.getComponents() == null || product.getComponents().isEmpty()) {
//            throw new IllegalStateException("Produto sem componentes cadastrados");
//        }
//        product.setReady(true);
    }

}
