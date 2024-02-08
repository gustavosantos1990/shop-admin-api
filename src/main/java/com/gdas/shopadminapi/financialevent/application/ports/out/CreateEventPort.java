package com.gdas.shopadminapi.financialevent.application.ports.out;

import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;

public interface CreateEventPort {
    FinancialEvent create(FinancialEvent financialEvent);
}
