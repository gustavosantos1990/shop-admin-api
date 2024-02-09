package com.gdas.shopadminapi.product.application.ports.in;

import com.gdas.shopadminapi.product.domain.Product;

import java.util.UUID;
import java.util.function.BiFunction;

public interface UpdateProductUseCase extends BiFunction<UUID, Product, Product> {
}
