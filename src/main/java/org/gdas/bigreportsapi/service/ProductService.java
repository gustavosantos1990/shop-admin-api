package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Component;
import org.gdas.bigreportsapi.model.entity.Product;
import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.model.entity.ProductComponentID;
import org.gdas.bigreportsapi.repository.ProductComponentRepository;
import org.gdas.bigreportsapi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductComponentRepository productComponentRepository;
    private final ComponentService componentService;
    private final CustomerService customerService;

    public ProductService(
            ProductRepository productRepository,
            ProductComponentRepository productComponentRepository,
            ComponentService componentService,
            CustomerService customerService) {
        this.productRepository = productRepository;
        this.productComponentRepository = productComponentRepository;
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
        Optional<Product> optionalProduct = cloneProduct(entity);
        return optionalProduct.orElseGet(() -> productRepository.save(entity));
    }

    private Optional<Product> cloneProduct(Product entity) {
        Product current;
        if (entity.getId() != null && (current = findByID(entity.getId())) != null) {
            Product clone = current.cloneProduct();
            return Optional.of(productRepository.save(clone));
        }
        return Optional.empty();
    }

    public Product delete(UUID id) {
        Product product = findByID(id);
        product.setDeletedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return productRepository.save(product);
    }

    public Product update(UUID id, Product entity) {
        Product product = findByID(id);
        product.setName(entity.getName());
        product.setPrice(entity.getPrice());
        product.setDescription(entity.getDescription());
        return productRepository.save(product);
    }

    public List<ProductComponent> findComponentsByProduct(UUID productID) {
        return productComponentRepository.findByProductComponentIDProductId(productID);
    }

    public ProductComponent saveComponent(UUID productID, ProductComponent newEntity) {
        Product product = findByID(productID);
        Component component = componentService.findByID(newEntity.getProductComponentID().getComponent().getId());

        if (product.getDeletedAt() != null) {
            throw new ResponseStatusException(PRECONDITION_FAILED, "Produto deletado");
        }

        newEntity.setProductComponentID(new ProductComponentID(product, component));
        if (newEntity.getProductComponentID().getComponent().getMeasure().isMultiDimension()) {
            newEntity.setAmount(newEntity.getHeight().multiply(newEntity.getWidth()));
        } else {
            newEntity.setHeight(BigDecimal.ZERO);
            newEntity.setWidth(BigDecimal.ZERO);
        }
        return productComponentRepository.save(newEntity);
    }

    public ProductComponent findByProductAndComponentIDs(UUID productID, String componentID) {
        return productComponentRepository.findByProductComponentIDProductIdAndProductComponentIDComponentId(productID, componentID)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid combination of product and component IDs"));
    }

    public void deleteProductComponent(UUID productID, String componentID) {
        ProductComponent productComponent = findByProductAndComponentIDs(productID, componentID);
        productComponentRepository.delete(productComponent);
    }
}
