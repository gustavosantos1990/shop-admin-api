package com.gdas.shopadminapi.financialevent.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "financial_event")
public class FinancialEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    public FinancialEvent() {
    }

    public FinancialEvent(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
