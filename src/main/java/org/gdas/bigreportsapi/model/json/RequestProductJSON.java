package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.gdas.bigreportsapi.model.actions.IncludingNewRequestProduct;
import org.gdas.bigreportsapi.model.entity.RequestProduct;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class RequestProductJSON {

    @JsonIgnore
    @JsonProperty
    private RequestJSON request;

    @JsonProperty
    @Valid
    @NotNull(groups = {IncludingNewRequestProduct.class})
    private ProductJSON product;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @JsonProperty("calculated_production_cost")
    private BigDecimal calculatedProductionCost;

    @JsonProperty("declared_production_cost")
    @NotNull(groups = {IncludingNewRequestProduct.class})
    @Positive(groups = {IncludingNewRequestProduct.class})
    private BigDecimal declaredProductionCost;

    @JsonProperty("unitary_value")
    @NotNull(groups = {IncludingNewRequestProduct.class})
    @Positive(groups = {IncludingNewRequestProduct.class})
    private BigDecimal unitaryValue;

    @JsonProperty
    @NotNull(groups = {IncludingNewRequestProduct.class})
    @Positive(groups = {IncludingNewRequestProduct.class})
    private BigDecimal amount;

    @JsonProperty
    private String notes;

    @JsonProperty("file_path")
    private String filePath;

    @JsonProperty("file_link")
    private String fileLink;

    public RequestProductJSON() {
    }

    public RequestJSON getRequest() {
        return request;
    }

    public void setRequest(RequestJSON request) {
        this.request = request;
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
        if (source.getRequestProductID() != null && source.getRequestProductID().getProduct() != null)
            target.setProduct(ProductJSON.from(source.getRequestProductID().getProduct()));
        return target;
    }

}
