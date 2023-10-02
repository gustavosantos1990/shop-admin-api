package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.model.json.ComponentJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "COMPONENT")
public class Component {

    @Id
    @Column(name = "CMP_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "CMP_CREATED_AT", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "CMP_UPDATED_AT", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "CMP_NAME", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "MEASURE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Measure measure;

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

    public static Component from(ComponentJSON json) {
        Component entity = new Component();
        entity.setId(json.getId());
        entity.setCreatedAt(json.getCreatedAt());
        entity.setName(json.getName());
        entity.setMeasure(Measure.valueOf(json.getMeasure().getValue()));
        return entity;
    }
}
