package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.gdas.bigreportsapi.model.entity.Revision;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RevisionJSON {

    @JsonProperty
    private Integer number;

    @JsonIgnore
    @JsonProperty
    private ProductJSON product;

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
    private String notes;

    private List<ProductComponentJSON> components = Collections.emptyList();

    public RevisionJSON() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<ProductComponentJSON> getComponents() {
        return components;
    }

    public void setComponents(List<ProductComponentJSON> components) {
        this.components = components;
    }

    public static RevisionJSON from(Revision source) {
        RevisionJSON target = new RevisionJSON();
        copyProperties(source, target);
        target.setNumber(source.getRevisionID().getNumber());
//        target.setComponents(source.getComponents().stream().map(ProductComponentJSON::from).collect(Collectors.toList()));
        return target;
    }

}
