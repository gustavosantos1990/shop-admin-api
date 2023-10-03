package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.gdas.bigreportsapi.model.entity.Product;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.beans.BeanUtils.copyProperties;

public class ProductJSON {

    @JsonProperty
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
    private boolean ready;

    @JsonProperty
    private List<ProductComponentJSON> components = Collections.emptyList();

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

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public List<ProductComponentJSON> getComponents() {
        return components;
    }

    public void setComponents(List<ProductComponentJSON> components) {
        this.components = components;
    }

    public static ProductJSON from(Product source) {
        ProductJSON target = new ProductJSON();
        copyProperties(source, target);
        return target;
    }

}
