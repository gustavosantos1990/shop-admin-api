package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.FindComponentByIdUseCase;
import com.gdas.shopadminapi.product.application.ports.out.FindComponentByIdPort;
import com.gdas.shopadminapi.product.domain.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
class FindComponentByIdService implements FindComponentByIdUseCase {

    private final FindComponentByIdPort findComponentByIdPort;

    FindComponentByIdService(FindComponentByIdPort findComponentByIdPort) {
        this.findComponentByIdPort = findComponentByIdPort;
    }

    @Override
    public Component apply(String id) {
        Optional<Component> optionalComponent = findComponentByIdPort.findById(id);
        return optionalComponent.orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid component ID (%s)", id)));
    }
}
