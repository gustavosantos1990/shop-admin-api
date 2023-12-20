package org.gdas.bigreportsapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.gdas.bigreportsapi.model.enummeration.FinancialMovementOperation;
import org.gdas.bigreportsapi.model.enummeration.Wallet;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "financial_movement")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FinancialMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fim_id")
    private Long id;

    @Column(name = "fim_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate date;

    @CreationTimestamp
    @Column(name = "fim_created_at", nullable = false, updatable = false)
    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "wallet", nullable = false, updatable = false)
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "operation", nullable = false, updatable = false)
    private FinancialMovementOperation operation;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "fim_rqt_id", updatable = false)
    private Request request;

//    @ManyToOne
//    @JoinColumn(name = "fim_acq_id", updatable = false)
//    private Acquisition acquisition;

    @Column(name = "fim_value", nullable = false)
    private BigDecimal value;

    @Column(name = "banking_operation", nullable = false)
    @JsonProperty("banking_operation")
    private boolean bankingOperation;

    @Column(name = "voucher_path")
    @JsonProperty("voucher_path")
    private String voucherPath;

    public FinancialMovement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public FinancialMovementOperation getOperation() {
        return operation;
    }

    public void setOperation(FinancialMovementOperation operation) {
        this.operation = operation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean isBankingOperation() {
        return bankingOperation;
    }

    public void setBankingOperation(boolean bankingOperation) {
        this.bankingOperation = bankingOperation;
    }

    public String getVoucherPath() {
        return voucherPath;
    }

    public void setVoucherPath(String voucherPath) {
        this.voucherPath = voucherPath;
    }
}
