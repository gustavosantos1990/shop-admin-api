package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.model.json.ComponentJSON;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "component")
public class Component {

    @Id
    @Column(name = "cmp_id", length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "cmp_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "cmp_deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "cmp_name", nullable = false, unique = true, updatable = false)
    private String name;

    @Column(name = "measure", nullable = false)
    @Enumerated(EnumType.STRING)
    private Measure measure;

    @Column(name = "photo_address")
    private String photoAddress;

    @Column(name = "base_buy_height", nullable = false)
    private BigDecimal baseBuyHeight;

    @Column(name = "base_buy_width", nullable = false)
    private BigDecimal baseBuyWidth;

    @Column(name = "base_buy_amount", nullable = false)
    private BigDecimal baseBuyAmount;

    @Column(name = "base_buy_paid_value", nullable = false)
    private BigDecimal baseBuyPaidValue;

    public Component() {
    }

    public Component(String name, Measure measure) {
        this.name = name;
        this.measure = measure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public BigDecimal getBaseBuyHeight() {
        return baseBuyHeight;
    }

    public void setBaseBuyHeight(BigDecimal baseBuyHeight) {
        this.baseBuyHeight = baseBuyHeight;
    }

    public BigDecimal getBaseBuyWidth() {
        return baseBuyWidth;
    }

    public void setBaseBuyWidth(BigDecimal baseBuyWidth) {
        this.baseBuyWidth = baseBuyWidth;
    }

    public BigDecimal getBaseBuyAmount() {
        return baseBuyAmount;
    }

    public void setBaseBuyAmount(BigDecimal baseBuyAmount) {
        this.baseBuyAmount = baseBuyAmount;
    }

    public BigDecimal getBaseBuyPaidValue() {
        return baseBuyPaidValue;
    }

    public void setBaseBuyPaidValue(BigDecimal baseBuyPaidValue) {
        this.baseBuyPaidValue = baseBuyPaidValue;
    }

    public static Component from(ComponentJSON source) {
        Component target = new Component();
        copyProperties(source, target);
        if (source.getMeasure() != null) target.setMeasure(Measure.valueOf(source.getMeasure().getCode()));
        return target;
    }

//    @PrePersist
//    public void prePersist() {
//        id = randomAlphanumeric(10);
//    }

}