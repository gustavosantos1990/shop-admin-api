package com.gdas.shopadminapi.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "product_component")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductComponent {

    @EmbeddedId
    @JsonUnwrapped
    @Valid
    private ProductComponentId productComponentId;

    @Column(nullable = false)
    @Min(0)
    private BigDecimal height;

    @Column(nullable = false)
    @Min(0)
    private BigDecimal width;

    @Column(nullable = false)
    @Min(0)
    private BigDecimal amount;

    @Transient
    private BigDecimal cost;

    public ProductComponent() {
    }

    public ProductComponent(ProductComponentId productComponentId) {
        this.productComponentId = productComponentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public ProductComponentId getProductComponentId() {
        return productComponentId;
    }

    public void setProductComponentId(ProductComponentId productComponentId) {
        this.productComponentId = productComponentId;
    }

    @PrePersist
    @PreUpdate
    private void prePersist() {
        if (productComponentId.getComponent().getMeasure().isMultiDimension()) {
            amount = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
            return;
        }
        height = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
        width = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
    }

    @PostLoad
    @PostPersist
    @PostUpdate
    private void post() {
        cost = calculateCost();
        if (productComponentId.getComponent().getMeasure().isMultiDimension()) {
            amount = height.multiply(width).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public BigDecimal calculateCost() {
        return productComponentId.getComponent().getMeasure().isMultiDimension()
                ? calculateCostFormMultiDimensionalComponent()
                : calculateCostFormSingleDimensionalComponent();
    }

    public BigDecimal calculateCostFormSingleDimensionalComponent() {
        BigDecimal paidValue = productComponentId.getComponent().getBaseBuyPaidValue();
        BigDecimal boughtAmount = productComponentId.getComponent().getBaseBuyAmount();
        return amount.multiply(paidValue).divide(boughtAmount, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateCostFormMultiDimensionalComponent() {
        BigDecimal paidValue = productComponentId.getComponent().getBaseBuyPaidValue();
        BigDecimal boughtAmount = productComponentId.getComponent().getBaseBuyHeight()
                .multiply(productComponentId.getComponent().getBaseBuyWidth());
        BigDecimal componentAmount = height.multiply(width);
        return componentAmount.multiply(paidValue).divide(boughtAmount, 2, RoundingMode.HALF_UP);
    }

//    public VersionComponent cloneProductComponent() {
//        VersionComponent target = new VersionComponent();
//        copyProperties(this, target);
//        return target;
//    }
//
//    public VersionComponent cloneForProduct(Product product) {
//        VersionComponent target = this.cloneProductComponent();
//        target.getProductComponentID().setProduct(product);
//        return target;
//    }
}
