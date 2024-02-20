package com.gdas.shopadminapi.request.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.gdas.shopadminapi.request.domain.converter.RequestProductDocumentConverter;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "request_product")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequestProduct {

    @EmbeddedId
    @JsonUnwrapped
    @Valid
    private RequestProductID requestProductID;

    @Column(name = "rpd_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @Convert(converter = RequestProductDocumentConverter.class)
    private RequestProductDocument document;

    @Column(name = "calculated_production_cost", nullable = false)
    @NotNull
    @Positive
    private BigDecimal calculatedProductionCost;

    @Column(name = "declared_production_cost", nullable = false)
    @NotNull
    @Positive
    private BigDecimal declaredProductionCost;

    @Column(name = "unitary_value", nullable = false)
    @NotNull
    @Positive
    private BigDecimal unitaryValue;

    @Column(name = "amount", nullable = false)
    @NotNull
    @Positive
    private BigDecimal amount;

    @Column(name = "notes")
    private String notes;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_link")
    private String fileLink;

    public RequestProduct() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestProduct that = (RequestProduct) o;
        return Objects.equals(requestProductID, that.requestProductID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestProductID);
    }

    public RequestProductID getRequestProductID() {
        return requestProductID;
    }

    public void setRequestProductID(RequestProductID requestProductID) {
        this.requestProductID = requestProductID;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public BigDecimal getCalculatedProductionCost() {
        return calculatedProductionCost;
    }

    public void setCalculatedProductionCost(BigDecimal calculatedProductionCost) {
        this.calculatedProductionCost = calculatedProductionCost;
    }

    public BigDecimal getDeclaredProductionCost() {
        return declaredProductionCost;
    }

    public void setDeclaredProductionCost(BigDecimal declaredProductionCost) {
        this.declaredProductionCost = declaredProductionCost;
    }

    public BigDecimal getUnitaryValue() {
        return unitaryValue;
    }

    public void setUnitaryValue(BigDecimal unitaryValue) {
        this.unitaryValue = unitaryValue;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public RequestProductDocument getDocument() {
        return document;
    }

    public void setDocument(RequestProductDocument document) {
        this.document = document;
    }

    //    @PrePersist
//    private void prePersist() {
//        calculatedProductionCost = requestProductID.getProduct().calculateProductionCost();
//    }
}
