package com.gdas.shopadminapi.request.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gdas.shopadminapi.product.domain.enumeration.Measure;

import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestProductComponent implements Serializable {

    private String component;

    private Measure measure;

    private BigDecimal boughtWidth;

    private BigDecimal boughtHeight;

    private BigDecimal boughtAmount;

    @JsonProperty("paid_value")
    private BigDecimal paidValue;

    private BigDecimal width;

    private BigDecimal height;

    private BigDecimal amount;

    public RequestProductComponent() {
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public BigDecimal getBoughtWidth() {
        return boughtWidth;
    }

    public void setBoughtWidth(BigDecimal boughtWidth) {
        this.boughtWidth = boughtWidth;
    }

    public BigDecimal getBoughtHeight() {
        return boughtHeight;
    }

    public void setBoughtHeight(BigDecimal boughtHeight) {
        this.boughtHeight = boughtHeight;
    }

    public BigDecimal getBoughtAmount() {
        return boughtAmount;
    }

    public void setBoughtAmount(BigDecimal boughtAmount) {
        this.boughtAmount = boughtAmount;
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
