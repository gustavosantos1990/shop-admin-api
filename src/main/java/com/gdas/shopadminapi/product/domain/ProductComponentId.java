package com.gdas.shopadminapi.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gdas.shopadminapi.product.application.ports.in.CreateProductComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.in.UpdateProductUseCase;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Embeddable
public class ProductComponentId {

    @ManyToOne(optional = false)
    @JoinColumn(name = "pco_pdt_id")
    @JsonIgnore
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pco_cmp_id")
    @NotNull(groups = {CreateProductComponentUseCase.class, UpdateProductUseCase.class})
    @Valid
    private Component component;

    public ProductComponentId() {
    }

    public ProductComponentId(Component component) {
        this.component = component;
    }

    public ProductComponentId(Product product, Component component) {
        this.product = product;
        this.component = component;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }

    public static ProductComponentId fromIds(UUID productId, String componentId) {
        return new ProductComponentId(new Product(productId), new Component(componentId));
    }

}
