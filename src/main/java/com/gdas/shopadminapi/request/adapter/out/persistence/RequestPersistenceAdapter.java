package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.application.ports.out.FindRequestByIdPort;
import com.gdas.shopadminapi.request.domain.Request;
import com.gdas.shopadminapi.request.application.ports.out.CreateRequestPort;
import com.gdas.shopadminapi.request.application.ports.out.FindAllRequestsPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class RequestPersistenceAdapter implements
        FindAllRequestsPort,
        FindRequestByIdPort,
        CreateRequestPort {

    private final RequestRepository requestRepository;

    RequestPersistenceAdapter(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request create(Request request) {
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
}
