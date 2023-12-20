package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.FinancialMovement;
import org.gdas.bigreportsapi.repository.FinancialMovementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialMovementService {

    private final FinancialMovementRepository financialMovementRepository;

    public FinancialMovementService(FinancialMovementRepository financialMovementRepository) {
        this.financialMovementRepository = financialMovementRepository;
    }

    public List<FinancialMovement> findAll() {
        return financialMovementRepository.findAll();
    }

    public FinancialMovement save(FinancialMovement payload) {
        return financialMovementRepository.save(payload);
    }
}
