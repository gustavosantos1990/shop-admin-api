package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.FinancialMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinancialMovementRepository extends JpaRepository<FinancialMovement, Long> {
}
