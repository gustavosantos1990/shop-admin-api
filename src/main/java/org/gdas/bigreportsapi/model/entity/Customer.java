package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.CustomerJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "ctm_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ctm_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "ctm_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "ctm_deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "ctm_name", nullable = false, updatable = false)
    private String name;

    @Column(name = "phone", nullable = false, updatable = false, unique = true)
    private String phone;

    public Customer() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static Customer from(CustomerJSON source) {
        Customer target = new Customer();
        copyProperties(source, target);
        return target;
    }

}
