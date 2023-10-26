package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.gdas.bigreportsapi.model.entity.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class ComponentJSON {

    @JsonProperty
    private String id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty
    @NotBlank
    private String name;

    @JsonProperty
    @NotNull
    @Valid
    private MeasureJSON measure;

    @JsonProperty("photo_address")
    private String photoAddress;

    @JsonProperty("base_buy_height")
    @Min(0)
    private BigDecimal baseBuyHeight;

    @JsonProperty("base_buy_width")
    @Min(0)
    private BigDecimal baseBuyWidth;

    @JsonProperty("base_buy_amount")
    @Min(0)
    private BigDecimal baseBuyAmount;

    @JsonProperty("base_buy_paid_value")
    @NotNull
    @Positive
    private BigDecimal baseBuyPaidValue;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public MeasureJSON getMeasure() {
        return measure;
    }

    public void setMeasure(MeasureJSON measure) {
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

    @AssertTrue(message = "check mandatory fields: non-multi: base_buy_amount, multi: base_buy_width and base_buy_height")
    @JsonIgnore
    public boolean isValid() {
        if (measure.toEnum().isMultiDimension()) {
            return baseBuyWidth != null && baseBuyHeight != null;
        }
        return baseBuyAmount != null;
    }

    public static ComponentJSON from(Component source) {
        ComponentJSON target = new ComponentJSON();
        copyProperties(source, target);
        target.setMeasure(MeasureJSON.from(source.getMeasure()));
        return target;
    }

}
