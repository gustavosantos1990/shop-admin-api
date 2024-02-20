package com.gdas.shopadminapi.product.application.ports.in;

import com.gdas.shopadminapi.product.domain.ProductComponent;

import java.util.function.Function;

public interface CreateProductComponentUseCase extends Function<ProductComponent, ProductComponent> {
}
