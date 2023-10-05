package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.gdas.bigreportsapi.model.entity.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.springframework.beans.BeanUtils.copyProperties;

public class RequestJSON {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull
    @Valid
    private CustomerJSON customer;

    @JsonIgnore
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @JsonProperty("canceled_at")
    private LocalDateTime canceledAt;

    @JsonProperty("due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING )
    @NotNull
    private LocalDate dueDate;

    @JsonProperty
    private String notes;

    @JsonProperty
    private boolean done;

    public RequestJSON() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerJSON getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerJSON customer) {
        this.customer = customer;
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

    public LocalDateTime getCanceledAt() {
        return canceledAt;
    }

    public void setCanceledAt(LocalDateTime canceledAt) {
        this.canceledAt = canceledAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public static RequestJSON from(Request source) {
        RequestJSON target = new RequestJSON();
        copyProperties(source, target);
        target.setCustomer(CustomerJSON.from(source.getCustomer()));
        return target;
    }
}
