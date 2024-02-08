package com.gdas.shopadminapi.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
@Table(name = "product_component")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductComponent {

    @EmbeddedId
    @JsonUnwrapped
    private ProductComponentId productComponentId;

    @Column(nullable = false)
    @Min(0)
    private BigDecimal height;

    @Column(nullable = false)
    @Min(0)
    private BigDecimal width;

    @Column(nullable = false)
    @Positive
    private BigDecimal amount;

    @Transient
    private BigDecimal cost;

    public ProductComponent() {
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

    //    @PostLoad
//    @PostPersist
//    @PostUpdate
//    public void post() {
//        BigDecimal paidValue = getProductComponentID().getComponent().getBaseBuyPaidValue();
//        BigDecimal boughtAmount = getProductComponentID().getComponent().getBaseBuyAmount();
//        cost = amount.multiply(paidValue).divide(boughtAmount, 2, RoundingMode.HALF_UP);
//    }
//
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
