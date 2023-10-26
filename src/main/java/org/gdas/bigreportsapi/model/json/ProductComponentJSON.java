package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.model.enummeration.Measure;

import java.math.BigDecimal;

import static org.springframework.beans.BeanUtils.copyProperties;

public class ProductComponentJSON {

    @JsonProperty
    private ProductJSON product;

    @JsonProperty
    @NotNull
    private ComponentJSON component;

    @JsonProperty
    @NotNull
    @Min(0)
    private BigDecimal height;

    @JsonProperty
    @NotNull
    @Min(0)
    private BigDecimal width;
    
    @JsonProperty
    @NotNull
    @Min(0)
    private BigDecimal amount;

    @JsonProperty
    private BigDecimal cost;

    public ProductJSON getProduct() {
        return product;
    }

    public void setProduct(ProductJSON product) {
        this.product = product;
    }

    public ComponentJSON getComponent() {
        return component;
    }

    public void setComponent(ComponentJSON component) {
        this.component = component;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @AssertTrue(message = "check mandatory fields: non-multi: amount, multi: width and height")
    @JsonIgnore
    public boolean isValid() {
        if (component.getMeasure().toEnum().isMultiDimension()) {
            return width != null && height != null;
        }
        return amount != null;
    }

    public static ProductComponentJSON from(ProductComponent source) {
        ProductComponentJSON target = new ProductComponentJSON();
        copyProperties(source, target);
//        target.setProduct(ProductJSON.from(source.getProductComponentID().getProduct()));
        target.setComponent(ComponentJSON.from(source.getProductComponentID().getComponent()));
        return target;
    }

}
