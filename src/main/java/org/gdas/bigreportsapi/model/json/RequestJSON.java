package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.gdas.bigreportsapi.model.actions.SavingNewRequest;
import org.gdas.bigreportsapi.model.actions.UpdatingRequest;
import org.gdas.bigreportsapi.model.entity.Request;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

public class RequestJSON {

    @JsonProperty
    private Long id;

    @JsonProperty
    @NotNull(groups = {SavingNewRequest.class, UpdatingRequest.class})
    @Valid
    private CustomerJSON customer;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("canceled_at")
    private LocalDateTime canceledAt;

    @JsonProperty("due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING )
    @NotNull(groups = {SavingNewRequest.class, UpdatingRequest.class})
    private LocalDate dueDate;

    @JsonProperty
    private String notes;

    @JsonProperty
    private boolean done;

    @JsonProperty("request_products")
    @NotNull(groups = {SavingNewRequest.class, UpdatingRequest.class})
    @NotEmpty(groups = {SavingNewRequest.class, UpdatingRequest.class})
    @Valid
    private List<RequestProductJSON> requestProducts;

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

    public List<RequestProductJSON> getRequestProducts() {
        return requestProducts;
    }

    public void setRequestProducts(List<RequestProductJSON> requestProducts) {
        this.requestProducts = requestProducts;
    }

    public static RequestJSON from(Request source) {
        RequestJSON target = new RequestJSON();
        copyProperties(source, target);
        target.setCustomer(CustomerJSON.from(source.getCustomer()));
        target.setRequestProducts(source.getRequestProducts().stream().map(RequestProductJSON::from).collect(Collectors.toList()));
        return target;
    }
}
