package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.Request;

import java.util.Optional;

public interface FindRequestByIdPort {
    Optional<Request> findById(Long requestId);
}
