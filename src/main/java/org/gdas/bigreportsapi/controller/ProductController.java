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
    public ResponseEntity<List<ProductJSON>> get() {
        List<Product> entities = productService.findAll();
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

    @PatchMapping("/{id}")
    public ResponseEntity<ProductJSON> patch(
            @PathVariable UUID id,
            @Valid @RequestBody ProductJSON payload) {
        Product entity = Product.from(payload);
        Product updated = productService.update(id, entity);
        return ResponseEntity.ok(ProductJSON.from(updated));
    }

    @PostMapping("/{id}/components")
    public ResponseEntity<ProductComponentJSON> postComponents(
            @PathVariable UUID id,
            @Valid @RequestBody ProductComponentJSON payload) {
        ProductComponent newEntity = ProductComponent.from(payload);
        ProductComponent saved = productService.saveComponent(id, newEntity);
        return ResponseEntity.status(CREATED).body(ProductComponentJSON.from(saved));
    }

}
