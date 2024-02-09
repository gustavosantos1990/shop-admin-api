package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.Product;

import java.util.Optional;
import java.util.UUID;

public interface FindProductByIdPort {
    Optional<Product> find(UUID id);
}
