package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.model.json.ProductComponentJSON;
import org.gdas.bigreportsapi.service.ProductsComponentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/products-components")
public class ProductsComponentsController {

    private final ProductsComponentsService productsComponentsService;

    public ProductsComponentsController(ProductsComponentsService productsComponentsService) {
        this.productsComponentsService = productsComponentsService;
    }

    @GetMapping
    public ResponseEntity<List<ProductComponentJSON>> get() {
        List<ProductComponent> entities = productsComponentsService.findAll();
        List<ProductComponentJSON> result = entities.stream().map(ProductComponentJSON::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}
