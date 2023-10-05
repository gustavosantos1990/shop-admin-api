package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.gdas.bigreportsapi.model.ActionsGroups;
import org.gdas.bigreportsapi.model.entity.RequestProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class RequestProductJSON {

    @JsonIgnore
    @JsonProperty
    @Valid
    @NotNull
    private RequestJSON request;

    @JsonProperty
    @Valid
    @NotNull(groups = {ActionsGroups.SavingRequestProduct.class})
    private ProductJSON product;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime updatedAt;

    @JsonIgnore
    @JsonProperty("deleted_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime deletedAt;

    @JsonProperty("unitary_value")
    @NotNull(groups = {ActionsGroups.SavingRequestProduct.class})
    @Positive(groups = {ActionsGroups.SavingRequestProduct.class})
    private BigDecimal unitaryValue;

    @JsonProperty
    @NotNull(groups = {ActionsGroups.SavingRequestProduct.class})
    @Positive(groups = {ActionsGroups.SavingRequestProduct.class})
    private BigDecimal amount;

    @JsonProperty
    private String notes;

    public RequestProductJSON() {
    }

    public RequestJSON getRequest() {
        return request;
    }

    public void setRequest(RequestJSON request) {
        this.request = request;
    }

    public ProductJSON getProduct() {
        return product;
    }

    public void setProduct(ProductJSON product) {
        this.product = product;
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

    public static RequestProductJSON from(RequestProduct source) {
        RequestProductJSON target = new RequestProductJSON();
        copyProperties(source, target);
//        target.setRequest(RequestJSON.from(source.getRequestProductID().getRequest()));
        target.setProduct(ProductJSON.from(source.getRequestProductID().getProduct()));
        return target;
    }

}
