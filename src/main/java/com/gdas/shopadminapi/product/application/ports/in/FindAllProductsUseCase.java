package com.gdas.shopadminapi.product.application.ports.in;

import com.gdas.shopadminapi.product.domain.Product;

import java.util.List;
import java.util.function.Supplier;

public interface FindAllProductsUseCase extends Supplier<List<Product>> {
}
