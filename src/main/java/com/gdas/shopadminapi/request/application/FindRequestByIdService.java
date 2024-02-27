package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.FindRequestByIdUseCase;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestByIdPort;
import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
class FindRequestByIdService implements FindRequestByIdUseCase {

    private final FindRequestByIdPort findRequestByIdPort;


    FindRequestByIdService(FindRequestByIdPort findRequestByIdPort) {
        this.findRequestByIdPort = findRequestByIdPort;
    }

    @Override
    public Request apply(Long requestId) {
        return findRequestByIdPort.findById(requestId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid request id: #%s", requestId)));
    }
}
