package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT r FROM Request r WHERE r.dueDate BETWEEN :startDate AND :endDate")
    Page<Request> findAllBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
