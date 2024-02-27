package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

interface RequestRepository extends JpaRepository<Request, Long> {

    List<Request> findByDueDateBetween(LocalDate startDate, LocalDate endDate);

}
