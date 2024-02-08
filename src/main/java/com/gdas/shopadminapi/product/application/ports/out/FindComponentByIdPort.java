package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.Component;

import java.util.Optional;

public interface FindComponentByIdPort {
    Optional<Component> findById(String id);
}
