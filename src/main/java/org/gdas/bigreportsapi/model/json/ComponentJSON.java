package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.gdas.bigreportsapi.model.entity.Component;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComponentJSON {

    @JsonProperty
    @NotNull
    private UUID id;

    @JsonIgnore
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty
    @NotBlank
    private String name;

    @JsonProperty
    @NotNull
    @Valid
    private MeasureJSON measure;

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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasureJSON getMeasure() {
        return measure;
    }

    public void setMeasure(MeasureJSON measure) {
        this.measure = measure;
    }

    public static ComponentJSON from(Component entity) {
        ComponentJSON json = new ComponentJSON();
        json.setId(entity.getId());
        json.setCreatedAt(entity.getCreatedAt());
        json.setUpdatedAt(entity.getUpdatedAt());
        json.setName(entity.getName());
        json.setMeasure(MeasureJSON.from(entity.getMeasure()));
        return json;
    }

}
