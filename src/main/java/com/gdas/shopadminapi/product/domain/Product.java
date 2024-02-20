package com.gdas.shopadminapi.product.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gdas.shopadminapi.product.application.ports.in.CreateProductUseCase;
import com.gdas.shopadminapi.product.application.ports.in.UpdateProductUseCase;
import com.gdas.shopadminapi.product.domain.enumeration.ProductStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

    //TODO: create update history

    @Id
    @Column(name = "pdt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "pdt_name", nullable = false, unique = true, updatable = false)
    @NotNull(groups = {CreateProductUseCase.class, UpdateProductUseCase.class})
    @NotBlank(groups = {CreateProductUseCase.class, UpdateProductUseCase.class})
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Column(name = "production_duration_in_minutes", nullable = false)
    @JsonProperty("production_duration_in_minutes")
    @NotNull(groups = {CreateProductUseCase.class, UpdateProductUseCase.class})
    @Positive(groups = {CreateProductUseCase.class, UpdateProductUseCase.class})
    private Integer productionDurationInMinutes;

    @Column(name = "pdt_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "pdt_deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "description")
    private String description;

    @Column(name = "price", nullable = false)
    @NotNull(groups = {CreateProductUseCase.class, UpdateProductUseCase.class})
    @Positive(groups = {CreateProductUseCase.class, UpdateProductUseCase.class})
    private BigDecimal price;

    @Column(name = "photo_address")
    private String photoAddress;

    public Product() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public int getProductionDurationInMinutes() {
        return productionDurationInMinutes;
    }

    public void setProductionDurationInMinutes(int productionDurationInMinutes) {
        this.productionDurationInMinutes = productionDurationInMinutes;
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
}