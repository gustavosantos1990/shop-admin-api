package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.ProductJSON;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "pdt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "pdt_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "pdt_deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "pdt_name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "photo_address")
    private String photoAddress;

    @OneToMany(mappedBy = "productComponentID.product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductComponent> components;

    @Column(name = "production_duration_in_minutes", nullable = false)
    private int productionDurationInMinutes;

    public Product() {
    }

    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
    }

    public List<ProductComponent> getComponents() {
        return components;
    }

    public void setComponents(List<ProductComponent> components) {
        this.components = components;
    }

    public static Product from(ProductJSON source) {
        Product target = new Product();
        copyProperties(source, target);
        return target;
    }

    public BigDecimal calculateProductionCost() {
        return components
                .stream()
                .map(ProductComponent::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Product cloneProduct() {
        Product target = new Product();
        copyProperties(this, target);
        target.setId(null);
        target.setCreatedAt(null);
        target.setName(format("Cópia de %s [%s]", this.getName(), randomAlphanumeric(6)));
        if (this.components != null && !this.components.isEmpty())
            target.setComponents(this.components
                    .stream()
                    .map(pc -> pc.cloneForProduct(target))
                    .collect(Collectors.toList()));
        return target;
    }

}