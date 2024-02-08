package com.gdas.shopadminapi.financialevent.application.ports.in;

import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;

import java.util.function.Function;

public interface CreateEventUseCase extends Function<FinancialEvent, FinancialEvent> {
}
