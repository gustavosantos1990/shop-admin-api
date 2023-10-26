package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT r FROM Request r WHERE r.dueDate BETWEEN :startDate AND :endDate ORDER BY createdAt DESC")
    List<Request> findAllBetweenOrderByCreatedAtDesc(LocalDate startDate, LocalDate endDate);

    @Query("SELECT r FROM Request r WHERE r.dueDate BETWEEN :startDate AND :endDate AND canceledAt IS NULL ORDER BY createdAt DESC")
    List<Request> findAllBetweenAndCanceledAtIsNullOrderByCreatedAtDesc(LocalDate startDate, LocalDate endDate);

}
