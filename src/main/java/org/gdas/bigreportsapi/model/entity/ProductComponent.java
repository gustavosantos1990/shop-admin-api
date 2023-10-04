package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.gdas.bigreportsapi.model.json.ProductComponentJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "product_component")
public class ProductComponent {

    @EmbeddedId
    private ProductComponentID productComponentID;

    @Column(name = "pco_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "pco_updated_at", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "unitary_value", nullable = false)
    private BigDecimal unitaryValue;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    public ProductComponent() {
    }

    public ProductComponentID getProductComponentID() {
        return productComponentID;
    }

    public void setProductComponentID(ProductComponentID productComponentID) {
        this.productComponentID = productComponentID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public BigDecimal getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(BigDecimal unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public static ProductComponent from(ProductComponentJSON source) {
        ProductComponent target = new ProductComponent();
        copyProperties(source, target);
        return target;
    }
}
