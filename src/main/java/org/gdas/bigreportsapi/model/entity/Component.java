package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.model.json.ComponentJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "component")
public class Component {

    @Id
    @Column(name = "cmp_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cmp_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "cmp_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "cmp_name", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "measure", nullable = false)
    @Enumerated(EnumType.STRING)
    private Measure measure;

    @Column(name = "photo_address")
    private String photoAddress;

    public Component() {
    }

    public Component(String name, Measure measure) {
        this.name = name;
        this.measure = measure;
    }

    public Component(UUID id, LocalDateTime createdAt, String name, Measure measure) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.measure = measure;
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

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
    }

    public static Component from(ComponentJSON source) {
        Component target = new Component();
        copyProperties(source, target);
        target.setMeasure(Measure.valueOf(source.getMeasure().getValue()));
        return target;
    }
}
