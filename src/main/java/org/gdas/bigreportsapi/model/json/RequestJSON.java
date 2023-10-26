package org.gdas.bigreportsapi.model.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.gdas.bigreportsapi.model.actions.SavingNewRequest;
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
    @NotNull(groups = {SavingNewRequest.class})
    @Valid
    private CustomerJSON customer;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("canceled_at")
    private LocalDateTime canceledAt;

    @JsonProperty("due_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(groups = {SavingNewRequest.class})
    private LocalDate dueDate;

    @JsonProperty
    private String notes;

    @JsonProperty
    private RequestStatusJSON status;

    @JsonProperty("request_products")
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

    public List<RequestProductJSON> getRequestProducts() {
        return requestProducts;
    }

    public void setRequestProducts(List<RequestProductJSON> requestProducts) {
        this.requestProducts = requestProducts;
    }

    public RequestStatusJSON getStatus() {
        return status;
    }

    public void setStatus(RequestStatusJSON status) {
        this.status = status;
    }

    public static RequestJSON from(Request source) {
        RequestJSON target = new RequestJSON();
        copyProperties(source, target);
        target.setCustomer(CustomerJSON.from(source.getCustomer()));
        if (source.getRequestProducts() != null) target.setRequestProducts(source.getRequestProducts().stream().map(RequestProductJSON::from).collect(Collectors.toList()));
        target.setStatus(RequestStatusJSON.from(source.getStatus()));
        return target;
    }
}
