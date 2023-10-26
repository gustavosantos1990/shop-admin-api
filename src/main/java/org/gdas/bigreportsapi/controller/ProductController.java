package org.gdas.bigreportsapi.controller;

import jakarta.validation.Valid;
import org.gdas.bigreportsapi.model.entity.Product;
import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.model.json.ProductComponentJSON;
import org.gdas.bigreportsapi.model.json.ProductJSON;
import org.gdas.bigreportsapi.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/products")
@Validated
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductJSON>> get(
            @RequestParam(name = "include_deleted", defaultValue = "false") boolean includeDeleted
    ) {
        List<Product> entities = productService.findAll(includeDeleted);
        List<ProductJSON> result = entities.stream().map(ProductJSON::from).collect(Collectors.toList());
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductJSON> getByID(@PathVariable UUID id) {
        Product entity = productService.findByID(id);
        return ResponseEntity.ok(ProductJSON.from(entity));
    }

    @PostMapping
    public ResponseEntity<ProductJSON> post(@Valid @RequestBody ProductJSON payload) {
        Product newEntity = Product.from(payload);
        Product saved = productService.save(newEntity);
        return ResponseEntity.status(CREATED).body(ProductJSON.from(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductJSON> delete(@PathVariable UUID id) {
        Product deleted = productService.delete(id);
        return ResponseEntity.ok(ProductJSON.from(deleted));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductJSON> put(
            @PathVariable UUID id,
            @Valid @RequestBody ProductJSON payload) {
        Product entity = Product.from(payload);
        Product updated = productService.update(id, entity);
        return ResponseEntity.ok(ProductJSON.from(updated));
    }

    @GetMapping("/{productID}/components")
    public ResponseEntity<List<ProductComponentJSON>> getComponents(@PathVariable UUID productID) {
        List<ProductComponent> componentsByProduct = productService.findComponentsByProduct(productID);
        List<ProductComponentJSON> result = componentsByProduct.stream().map(ProductComponentJSON::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{productID}/components")
    public ResponseEntity<ProductComponentJSON> postComponent(
            @PathVariable UUID productID,
            @Valid @RequestBody ProductComponentJSON payload) {
        ProductComponent newEntity = ProductComponent.from(payload);
        ProductComponent saved = productService.saveComponent(productID, newEntity);
        return ResponseEntity.status(CREATED).body(ProductComponentJSON.from(saved));
    }

    @DeleteMapping("/{productID}/components/{componentID}")
    public ResponseEntity<Void> deleteComponent(
            @PathVariable UUID productID,
            @PathVariable String componentID) {
        productService.deleteProductComponent(productID, componentID);
        return ResponseEntity.noContent().build();
    }

}
