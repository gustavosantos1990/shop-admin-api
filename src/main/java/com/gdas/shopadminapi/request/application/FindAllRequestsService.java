package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.FindAllRequestsUseCase;
import com.gdas.shopadminapi.request.application.ports.out.FindAllRequestsPort;
import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FindAllRequestsService implements FindAllRequestsUseCase {

    private final FindAllRequestsPort findAllRequestsPort;

    FindAllRequestsService(FindAllRequestsPort findAllRequestsPort) {
        this.findAllRequestsPort = findAllRequestsPort;
    }

    @Override
    public List<Request> get() {
        return findAllRequestsPort.findAll();
    }
}
