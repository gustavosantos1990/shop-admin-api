package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.application.ports.out.SaveRequestPort;
import com.gdas.shopadminapi.request.application.ports.out.FindAllRequestsPort;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestByIdPort;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestsByDueDateBetweenPort;
import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
class RequestPersistenceAdapter implements
        FindAllRequestsPort,
        FindRequestsByDueDateBetweenPort,
        FindRequestByIdPort,
        SaveRequestPort {

    private final RequestRepository requestRepository;

    RequestPersistenceAdapter(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request save(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public Optional<Request> findById(Long requestId) {
        return requestRepository.findById(requestId);
    }

    @Override
    public List<Request> findAllBetween(LocalDate startDate, LocalDate endDate) {
        return requestRepository.findByDueDateBetween(startDate, endDate);
    }
}
