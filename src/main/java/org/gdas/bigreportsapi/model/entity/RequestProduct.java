package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.gdas.bigreportsapi.model.json.RequestProductJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "rpd_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "rpd_deleted_at", updatable = false)
    private LocalDateTime deletedAt;

    @Column(name = "unitary_value", nullable = false)
    private BigDecimal unitaryValue;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "notes")
    private String notes;

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

    public static RequestProduct from(RequestProductJSON source) {
        RequestProduct target = new RequestProduct();
        copyProperties(source, target);
        target.setRequestProductID(RequestProductID.from(source));
        return target;
    }
}
