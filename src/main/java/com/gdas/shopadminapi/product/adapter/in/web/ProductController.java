package com.gdas.shopadminapi.product.adapter.in.web;

import com.gdas.shopadminapi.product.application.ports.in.CreateProductUseCase;
import com.gdas.shopadminapi.product.application.ports.in.FindAllProductsUseCase;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final FindAllProductsUseCase findAllProductsUseCase;
    private final CreateProductUseCase createProductUseCase;

    public ProductController(FindAllProductsUseCase findAllProductsUseCase, CreateProductUseCase createProductUseCase) {
        this.findAllProductsUseCase = findAllProductsUseCase;
        this.createProductUseCase = createProductUseCase;
    }

    @GetMapping
    private ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = findAllProductsUseCase.get();
        return ResponseEntity.ok(products);
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

}
