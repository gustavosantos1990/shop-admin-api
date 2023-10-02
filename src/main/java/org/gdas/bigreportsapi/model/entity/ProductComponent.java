package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.ProductComponentJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT_COMPONENT")
public class ProductComponent {

    @Id
    @Column(name = "PCO_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PDT_ID")
    private Product product;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CMP_ID")
    private Component component;

    @Column(name = "PCO_CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "PCO_UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "UNITARY_VALUE", nullable = false)
    private BigDecimal unitaryValue;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    public ProductComponent() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public static ProductComponent from(ProductComponentJSON json) {
        ProductComponent entity = new ProductComponent();
        entity.setId(json.getId());
        entity.setCreatedAt(json.getCreatedAt());
        entity.setUpdatedAt(json.getUpdatedAt());
        entity.setComponent(Component.from(json.getComponent()));
        entity.setUnitaryValue(json.getUnitaryValue());
        entity.setAmount(json.getAmount());
        return entity;
    }
}
