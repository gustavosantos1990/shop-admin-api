package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FindRequestsByDueDateBetweenPort {
    List<Request> findAllBetween(LocalDate startDate, LocalDate endDate);
}
