package com.gdas.shopadminapi.financialevent.adapter.out.persistence;

import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface FinancialEventRepository extends JpaRepository<FinancialEvent, UUID> {
}
