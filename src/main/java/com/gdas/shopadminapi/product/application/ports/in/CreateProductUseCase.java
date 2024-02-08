package com.gdas.shopadminapi.product.application.ports.in;

import com.gdas.shopadminapi.product.domain.Product;

import java.util.function.Function;

public interface CreateProductUseCase extends Function<Product, Product> {
}
