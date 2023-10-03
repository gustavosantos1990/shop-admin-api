package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.ProductJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @Column(name = "PDT_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "PDT_CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "PDT_UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "SRC_PDT_ID")
    private Product sourceProduct;

    @Column(name = "NAME", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "READY", nullable = false, updatable = false)
    private boolean ready;

    @OneToMany(mappedBy = "product")
    private List<ProductComponent> components = Collections.emptyList();

    public Product() {
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public List<ProductComponent> getComponents() {
        return components;
    }

    public Product getSourceProduct() {
        return sourceProduct;
    }

    public void setSourceProduct(Product sourceProduct) {
        this.sourceProduct = sourceProduct;
    }

    public void setComponents(List<ProductComponent> components) {
        this.components = components;
    }

        public static Product from(ProductJSON source) {
        Product target = new Product();
        copyProperties(source, target);
        return target;
    }
}
