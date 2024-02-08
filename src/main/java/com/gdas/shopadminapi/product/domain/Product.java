package com.gdas.shopadminapi.product.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.gdas.shopadminapi.product.domain.enumeration.ProductStatus;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {

    @Id
    @Column(name = "pdt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "pdt_name", nullable = false, unique = true)
    private String name;

    @Transient
    private ProductStatus status;

    public Product() {
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

    //    @Column(name = "production_duration_in_minutes", nullable = false)
//    private int productionDurationInMinutes;
//
//    @Column(name = "pdt_created_at", nullable = false)
//    @CreationTimestamp
//    private LocalDateTime createdAt;
//
//    @Column(name = "pdt_deleted_at")
//    private LocalDateTime deletedAt;
//
//    @Column(name = "description")
//    private String description;
//
//    @Column(name = "price", nullable = false)
//    private BigDecimal price;
//
//    @Column(name = "photo_address")
//    private String photoAddress;

}