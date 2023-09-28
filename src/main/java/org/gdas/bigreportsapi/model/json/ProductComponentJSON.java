package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.model.enummeration.Measure;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductComponentJSON {

    @JsonProperty
    private UUID id;

    @JsonProperty
    private LocalDateTime createdAt;

    @JsonProperty
    private String name;

    @JsonProperty
    private Measure measure;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    public static ProductComponentJSON from(ProductComponent entity) {
        ProductComponentJSON json = new ProductComponentJSON();
        json.setId(entity.getId());
        json.setCreatedAt(entity.getCreatedAt());
        json.setName(entity.getName());
        json.setMeasure(entity.getMeasure());
        return json;
    }

}
