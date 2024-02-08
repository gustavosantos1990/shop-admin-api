package com.gdas.shopadminapi.financialevent.application;

import com.gdas.shopadminapi.financialevent.application.ports.in.FindAllEventsUseCase;
import com.gdas.shopadminapi.financialevent.application.ports.out.FindAllEventsPort;
import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FindAllEventsService implements FindAllEventsUseCase {

    private final FindAllEventsPort findAllEventsPort;

    FindAllEventsService(FindAllEventsPort findAllEventsPort) {
        this.findAllEventsPort = findAllEventsPort;
    }

    @Override
    public List<FinancialEvent> get() {
        return findAllEventsPort.findAll();
    }

}
