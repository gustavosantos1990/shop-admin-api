package com.gdas.shopadminapi.financialevent.application.ports.out;

import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;

import java.util.List;

public interface FindAllEventsPort {
    List<FinancialEvent> findAll();
}
