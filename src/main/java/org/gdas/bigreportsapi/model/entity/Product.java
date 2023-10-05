package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.ProductJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "pdt_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cloned_from")
    private Product clonedFrom;

    @Column(name = "pdt_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "pdt_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "pdt_deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "pdt_name", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getClonedFrom() {
        return clonedFrom;
    }

    public void setClonedFrom(Product clonedFrom) {
        this.clonedFrom = clonedFrom;
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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public static Product from(ProductJSON source) {
        Product target = new Product();
        copyProperties(source, target);
        return target;
    }
}
