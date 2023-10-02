package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.gdas.bigreportsapi.model.entity.ProductComponent;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class ProductComponentJSON {

    @JsonProperty
    private UUID id;

    @JsonProperty
    @NotNull
    @Valid
    private ComponentJSON component;

    @JsonIgnore
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("unitary_value")
    @NotNull
    @Positive
    private BigDecimal unitaryValue;

    @JsonProperty
    @NotNull
    @Positive
    private BigDecimal amount;

    public ProductComponentJSON() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ComponentJSON getComponent() {
        return component;
    }

    public void setComponent(ComponentJSON component) {
        this.component = component;
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

    public static ProductComponentJSON from(ProductComponent entity) {
        ProductComponentJSON json = new ProductComponentJSON();
        json.setId(entity.getId());
        json.setCreatedAt(entity.getCreatedAt());
        json.setUpdatedAt(entity.getUpdatedAt());
        json.setComponent(ComponentJSON.from(entity.getComponent()));
        json.setUnitaryValue(entity.getUnitaryValue());
        json.setAmount(entity.getAmount());
        return json;
    }

}
