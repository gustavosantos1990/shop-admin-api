package com.gdas.shopadminapi.acquisition.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gdas.shopadminapi.product.application.ports.in.CreateComponentUseCase;
import com.gdas.shopadminapi.product.domain.Component;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "component_acquisition")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComponentAcquisition {

    @Id
    @Column(name = "cac_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cac_cmp_id")
    private Component component;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cac_acq_id")
    private Acquisition acquisition;

    @NotNull(groups = {CreateComponentUseCase.class})
    @Column(nullable = false)
    private BigDecimal height;

    @NotNull(groups = {CreateComponentUseCase.class})
    @Column(nullable = false)
    private BigDecimal width;

    @NotNull(groups = {CreateComponentUseCase.class})
    @Column(nullable = false)
    private BigDecimal amount;

    @NotNull(groups = {CreateComponentUseCase.class})
    @Column(name = "paid_value", nullable = false)
    @JsonProperty("paid_value")
    private BigDecimal paidValue;

    public ComponentAcquisition() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(BigDecimal paidValue) {
        this.paidValue = paidValue;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}