package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.gdas.bigreportsapi.model.actions.IncludingNewRequestProduct;
import org.gdas.bigreportsapi.model.actions.SavingProduct;
import org.gdas.bigreportsapi.model.entity.Product;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

public class ProductJSON {

    @JsonProperty
    @NotNull(groups = {IncludingNewRequestProduct.class})
    private UUID id;

    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime createdAt;

    @JsonProperty("deleted_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime deletedAt;

    @JsonProperty
    @NotBlank(groups = {SavingProduct.class})
    @Size(min = 2, groups = {SavingProduct.class})
    private String name;

    @JsonProperty
    private String description;

    @JsonProperty
    @NotNull(groups = {SavingProduct.class})
    @Positive(groups = {SavingProduct.class})
    private BigDecimal price;

    @JsonProperty("photo_address")
    private String photoAddress;

    @JsonProperty
    private List<ProductComponentJSON> components;

    @JsonProperty("production_duration_in_minutes")
    @Positive(groups = {SavingProduct.class})
    private int productionDurationInMinutes;

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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPhotoAddress() {
        return photoAddress;
    }

    public void setPhotoAddress(String photoAddress) {
        this.photoAddress = photoAddress;
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
        if(source.getComponents() != null)
            target.setComponents(source.getComponents()
                    .stream()
                    .map(ProductComponentJSON::from)
                    .collect(Collectors.toList()));
        return target;
    }

}
