package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.gdas.bigreportsapi.model.json.ProductComponentJSON;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "product_component")
public class ProductComponent {

    @EmbeddedId
    private ProductComponentID productComponentID;

    @Column(name = "height", nullable = false)
    @Min(0)
    private BigDecimal height;

    @Column(name = "width", nullable = false)
    @Min(0)
    private BigDecimal width;

    @Column(name = "amount", nullable = false)
    @Positive
    private BigDecimal amount;

    @Transient
    private BigDecimal cost;

    public ProductComponent() {
    }

    public ProductComponentID getProductComponentID() {
        return productComponentID;
    }

    public void setProductComponentID(ProductComponentID productComponentID) {
        this.productComponentID = productComponentID;
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

    @PostLoad
    @PostPersist
    @PostUpdate
    public void post() {
        BigDecimal paidValue = getProductComponentID().getComponent().getBaseBuyPaidValue();
        BigDecimal boughtAmount = getProductComponentID().getComponent().getBaseBuyAmount();
        cost = amount.multiply(paidValue).divide(boughtAmount, 2, RoundingMode.HALF_UP);
    }

    public static ProductComponent from(ProductComponentJSON source) {
        ProductComponent target = new ProductComponent();
        copyProperties(source, target);
        target.setProductComponentID(new ProductComponentID(Component.from(source.getComponent())));
        return target;
    }

}
