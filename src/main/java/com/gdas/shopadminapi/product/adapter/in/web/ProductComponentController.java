package com.gdas.shopadminapi.product.adapter.in.web;

import com.gdas.shopadminapi.product.application.ports.in.CreateProductComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.in.DeleteProductComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.in.FindAllProductComponentsUseCase;
import com.gdas.shopadminapi.product.application.ports.in.UpdateProductComponentUseCase;
import com.gdas.shopadminapi.product.domain.Component;
import com.gdas.shopadminapi.product.domain.Product;
import com.gdas.shopadminapi.product.domain.ProductComponent;
import com.gdas.shopadminapi.product.domain.ProductComponentId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/products/{productId}/components")
public class ProductComponentController {

    private final FindAllProductComponentsUseCase findAllProductComponentsUseCase;
    private final CreateProductComponentUseCase createProductComponentUseCase;
    private final UpdateProductComponentUseCase updateProductComponentUseCase;
    private final DeleteProductComponentUseCase deleteProductComponentUseCase;

    public ProductComponentController(FindAllProductComponentsUseCase findAllProductComponentsUseCase, CreateProductComponentUseCase createProductComponentUseCase, UpdateProductComponentUseCase updateProductComponentUseCase, DeleteProductComponentUseCase deleteProductComponentUseCase) {
        this.findAllProductComponentsUseCase = findAllProductComponentsUseCase;
        this.createProductComponentUseCase = createProductComponentUseCase;
        this.updateProductComponentUseCase = updateProductComponentUseCase;
        this.deleteProductComponentUseCase = deleteProductComponentUseCase;
    }

    @GetMapping
    private ResponseEntity<List<ProductComponent>> findAllProductComponents(
            @PathVariable UUID productId
    ) {
        List<ProductComponent> productComponents = findAllProductComponentsUseCase.apply(productId);
        return ResponseEntity.ok(productComponents);
    }

    @PostMapping
    private ResponseEntity<ProductComponent> post(
            @PathVariable UUID productId,
            @Validated(CreateProductComponentUseCase.class) @RequestBody ProductComponent productComponent) {
        productComponent.getProductComponentId().setProduct(new Product(productId));

        ProductComponent newProductComponent = createProductComponentUseCase.apply(productComponent);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(format("/%s", newProductComponent.getProductComponentId().getComponent().getId()))
                .buildAndExpand(newProductComponent)
                .toUri();

        return ResponseEntity.created(uri).body(newProductComponent);
    }

    @PutMapping("/{componentId}")
    private ResponseEntity<ProductComponent> put(
            @PathVariable UUID productId,
            @PathVariable String componentId,
            @Validated(UpdateProductComponentUseCase.class) @RequestBody ProductComponent productComponent) {
        productComponent.setProductComponentId(new ProductComponentId(new Product(productId), new Component(componentId)));
        ProductComponent updatedProductComponent = updateProductComponentUseCase.apply(productComponent);
        return ResponseEntity.ok(updatedProductComponent);
    }

    @DeleteMapping("/{componentId}")
    private ResponseEntity<ProductComponent> put(
            @PathVariable UUID productId,
            @PathVariable String componentId) {
        deleteProductComponentUseCase.accept(productId, componentId);
        return ResponseEntity.noContent().build();
    }

}
