package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.FindProductByIdUseCase;
import com.gdas.shopadminapi.product.application.ports.out.FindProductByIdPort;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
class FindProductByIdService implements FindProductByIdUseCase {

    private final FindProductByIdPort findProductByIdPort;

    FindProductByIdService(FindProductByIdPort findProductByIdPort) {
        this.findProductByIdPort = findProductByIdPort;
    }

    @Override
    public Product apply(UUID uuid) {
        return findProductByIdPort.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("invalid product id (%s)", uuid)));
    }
}
