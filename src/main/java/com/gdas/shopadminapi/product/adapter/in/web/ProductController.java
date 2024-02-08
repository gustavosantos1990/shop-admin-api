package com.gdas.shopadminapi.product.adapter.in.web;

import com.gdas.shopadminapi.product.application.ports.in.FindAllProductsUseCase;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final FindAllProductsUseCase findAllProductsUseCase;

    public ProductController(FindAllProductsUseCase findAllProductsUseCase) {
        this.findAllProductsUseCase = findAllProductsUseCase;
    }

    @GetMapping
    private ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = findAllProductsUseCase.get();
        return ResponseEntity.ok(products);
    }

}
