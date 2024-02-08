package com.gdas.shopadminapi.financialevent.adapter.out.persistence;

import com.gdas.shopadminapi.financialevent.application.ports.out.CreateEventPort;
import com.gdas.shopadminapi.financialevent.application.ports.out.FindAllEventsPort;
import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class FinancialEventPersistenceAdapter implements FindAllEventsPort, CreateEventPort {

    private final FinancialEventRepository financialEventRepository;

    FinancialEventPersistenceAdapter(FinancialEventRepository financialEventRepository) {
        this.financialEventRepository = financialEventRepository;
    }

    @Override
    public FinancialEvent create(FinancialEvent financialEvent) {
        return financialEventRepository.save(financialEvent);
    }

    @Override
    public List<FinancialEvent> findAll() {
        return financialEventRepository.findAll();
    }
}
