package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.gdas.bigreportsapi.model.enummeration.RequestStatus;
import org.gdas.bigreportsapi.model.json.RequestJSON;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.copyProperties;

@Entity
@Table(name = "request")
public class Request {

    @Id
    @Column(name = "rqt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rqt_ctm_id")
    @NotNull
    private Customer customer;

    @Column(name = "rqt_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "rqt_canceled_at", updatable = false)
    private LocalDateTime canceledAt;

    @Column(name = "due_date", nullable = false)
    @NotNull
    private LocalDate dueDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull
    private RequestStatus status;

    @OneToMany(mappedBy = "requestProductID.request")
    private List<RequestProduct> requestProducts;

    public Request() {
    }

    public Request(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) && Objects.equals(customer, request.customer) && Objects.equals(createdAt, request.createdAt) && Objects.equals(dueDate, request.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, createdAt, dueDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
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

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public List<RequestProduct> getRequestProducts() {
        return requestProducts;
    }

    public void setRequestProducts(List<RequestProduct> requestProducts) {
        this.requestProducts = requestProducts;
    }

    public static Request from(RequestJSON source) {
        Request target = new Request();
        copyProperties(source, target);
        if (source.getCustomer() != null) target.setCustomer(Customer.from(source.getCustomer()));
        if (source.getRequestProducts() != null) target.setRequestProducts(source.getRequestProducts().stream().map(RequestProduct::from).collect(Collectors.toList()));
        return target;
    }

}
