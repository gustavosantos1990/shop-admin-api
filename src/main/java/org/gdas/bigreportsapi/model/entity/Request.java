package org.gdas.bigreportsapi.model.entity;

import jakarta.persistence.*;
import org.gdas.bigreportsapi.model.json.RequestJSON;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
    private Customer customer;

    @Column(name = "rqt_created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "rqt_updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(name = "rqt_deleted_at", updatable = false)
    private LocalDateTime deletedAt;

    @Column(name = "rqt_canceled_at", updatable = false)
    private LocalDateTime canceledAt;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "done", nullable = false)
    private boolean done;

    @OneToMany(mappedBy = "requestProductID.request")
    private List<RequestProduct> products;

    public Request() {
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

    public List<RequestProduct> getProducts() {
        return products;
    }

    public void setProducts(List<RequestProduct> products) {
        this.products = products;
    }

    public static Request from(RequestJSON source) {
        Request target = new Request();
        copyProperties(source, target);
        target.setCustomer(Customer.from(source.getCustomer()));
        target.setProducts(source.getProducts().stream().map(RequestProduct::from).collect(Collectors.toList()));
        return target;
    }

}
