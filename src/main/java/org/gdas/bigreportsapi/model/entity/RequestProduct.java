package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.RequestProductJSON;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "request_product")
public class RequestProduct {

    @EmbeddedId
    private RequestProductID requestProductID;

    @Column(name = "rpd_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "calculated_production_cost", nullable = false)
    //TODO: include hibernate validator annotations in entity (ex.: @NotNull)
    private BigDecimal calculatedProductionCost;

    @Column(name = "declared_production_cost", nullable = false)
    private BigDecimal declaredProductionCost;

    @Column(name = "unitary_value", nullable = false)
    private BigDecimal unitaryValue;

    @Column(name = "amount", nullable = false)
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

    public static RequestProduct from(RequestProductJSON source) {
        RequestProduct target = new RequestProduct();
        copyProperties(source, target);
        if (source.getProduct() != null) target.setRequestProductID(RequestProductID.from(source));
        return target;
    }

    @PrePersist
    private void prePersist() {
        calculatedProductionCost = requestProductID.getProduct().calculateProductionCost();
    }
}
