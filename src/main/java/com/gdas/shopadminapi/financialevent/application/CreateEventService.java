package com.gdas.shopadminapi.financialevent.application;

import com.gdas.shopadminapi.financialevent.application.ports.in.CreateEventUseCase;
import com.gdas.shopadminapi.financialevent.application.ports.out.CreateEventPort;
import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;
import org.springframework.stereotype.Service;

@Service
class CreateEventService implements CreateEventUseCase {

    private final CreateEventPort createEventPort;

    CreateEventService(CreateEventPort createEventPort) {
        this.createEventPort = createEventPort;
    }

    @Override
    public FinancialEvent apply(FinancialEvent financialEvent) {
        return createEventPort.create(financialEvent);
    }
}
