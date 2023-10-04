package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.ProductJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "pdt_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "pdt_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "src_pdt_id")
    private Product sourceProduct;

    @Column(name = "pdt_name", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "ready", nullable = false, updatable = false)
    private boolean ready;

    @Column(name = "photo_address")
    private String photoAddress;

//    @OneToMany(mappedBy = "revisionID.product")
//    private List<Revision> revisions = Collections.emptyList();

    public Product() {
    }

    public Product(String name) {
        this.name = name;
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

    public Product getSourceProduct() {
        return sourceProduct;
    }

    public void setSourceProduct(Product sourceProduct) {
        this.sourceProduct = sourceProduct;
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

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
    }

//    public List<Revision> getRevisions() {
//        return revisions;
//    }
//
//    public void setRevisions(List<Revision> revisions) {
//        this.revisions = revisions;
//    }

    public static Product from(ProductJSON source) {
        Product target = new Product();
        copyProperties(source, target);
        return target;
    }
}
