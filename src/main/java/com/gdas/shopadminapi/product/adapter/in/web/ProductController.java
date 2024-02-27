package com.gdas.shopadminapi.product.adapter.in.web;

import com.gdas.shopadminapi.product.application.ports.in.CreateProductUseCase;
import com.gdas.shopadminapi.product.application.ports.in.FindAllProductsUseCase;
import com.gdas.shopadminapi.product.application.ports.in.FindProductByIdUseCase;
import com.gdas.shopadminapi.product.application.ports.in.UpdateProductUseCase;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final FindAllProductsUseCase findAllProductsUseCase;
    private final FindProductByIdUseCase findProductByIdUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController(FindAllProductsUseCase findAllProductsUseCase, FindProductByIdUseCase findProductByIdUseCase, CreateProductUseCase createProductUseCase, UpdateProductUseCase updateProductUseCase) {
        this.findAllProductsUseCase = findAllProductsUseCase;
        this.findProductByIdUseCase = findProductByIdUseCase;
        this.createProductUseCase = createProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
    }

    @GetMapping
    private ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = findAllProductsUseCase.get();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{productId}")
    private ResponseEntity<Product> findById(@PathVariable UUID productId) {
        Product product = findProductByIdUseCase.apply(productId);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    private ResponseEntity<Product> post(
            @Validated(CreateProductUseCase.class) @RequestBody Product product) {
        Product newProduct = createProductUseCase.apply(product);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(format("/%s", newProduct.getId()))
                .buildAndExpand(newProduct)
                .toUri();
        return ResponseEntity.created(uri).body(newProduct);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Product> put(
            @PathVariable UUID id,
            @Validated(UpdateProductUseCase.class) @RequestBody Product product) {
        Product newProduct = updateProductUseCase.apply(id, product);
        return ResponseEntity.ok(newProduct);
    }

}
