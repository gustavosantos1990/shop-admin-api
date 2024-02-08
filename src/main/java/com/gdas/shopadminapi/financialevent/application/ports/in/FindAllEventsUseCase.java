package com.gdas.shopadminapi.financialevent.application.ports.in;

import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;

import java.util.List;
import java.util.function.Supplier;

public interface FindAllEventsUseCase extends Supplier<List<FinancialEvent>> {
}
