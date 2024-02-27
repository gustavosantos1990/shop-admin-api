package com.gdas.shopadminapi.product.application.ports.in;

import com.gdas.shopadminapi.product.domain.Product;

import java.util.UUID;
import java.util.function.Function;

public interface FindProductByIdUseCase extends Function<UUID, Product> {
}
