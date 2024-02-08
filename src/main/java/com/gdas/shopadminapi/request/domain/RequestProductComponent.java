package com.gdas.shopadminapi.request.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gdas.shopadminapi.product.domain.enumeration.Measure;

import java.math.BigDecimal;

public class RequestProductComponent {

    private String component;
    private Measure measure;
    private BigDecimal width;
    private BigDecimal height;
    private BigDecimal amount;
    @JsonProperty("paid_value")
    private BigDecimal paidValue;

    public RequestProductComponent() {
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
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
}
