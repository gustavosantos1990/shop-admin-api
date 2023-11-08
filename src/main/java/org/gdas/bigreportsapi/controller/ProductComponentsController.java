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
@RequestMapping("/v1/products/{productID}/components")
@Validated
public class ProductComponentsController {

    private final ProductService productService;

    public ProductComponentsController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductComponentJSON>> getComponents(@PathVariable UUID productID) {
        List<ProductComponent> componentsByProduct = productService.findComponentsByProduct(productID);
        List<ProductComponentJSON> result = componentsByProduct.stream().map(ProductComponentJSON::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<ProductComponentJSON> postComponent(
            @PathVariable UUID productID,
            @Valid @RequestBody ProductComponentJSON payload) {
        ProductComponent newEntity = ProductComponent.from(payload);
        ProductComponent saved = productService.saveComponent(productID, newEntity);
        return ResponseEntity.status(CREATED).body(ProductComponentJSON.from(saved));
    }

    @PutMapping("/{componentID}")
    public ResponseEntity<ProductComponentJSON> putComponent(
            @PathVariable UUID productID,
            @PathVariable String componentID,
            @Valid @RequestBody ProductComponentJSON payload) {
        ProductComponent newEntity = ProductComponent.from(payload);
        ProductComponent updated = productService.updateComponent(productID, componentID, newEntity);
        return ResponseEntity.ok(ProductComponentJSON.from(updated));
    }

    @DeleteMapping("/{componentID}")
    public ResponseEntity<Void> deleteComponent(
            @PathVariable UUID productID,
            @PathVariable String componentID) {
        productService.deleteProductComponent(productID, componentID);
        return ResponseEntity.noContent().build();
    }

}
