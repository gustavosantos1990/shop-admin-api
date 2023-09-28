package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.enummeration.Measure;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PRODUCT_COMPONENT")
public class ProductComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Measure measure;

    public ProductComponent() {
    }

    public ProductComponent(LocalDateTime createdAt, String name, Measure measure) {
        this.createdAt = createdAt;
        this.name = name;
        this.measure = measure;
    }

    public ProductComponent(UUID id, LocalDateTime createdAt, String name, Measure measure) {
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
}
