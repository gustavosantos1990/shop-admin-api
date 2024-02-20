package com.gdas.shopadminapi.product.application.ports.in;

import com.gdas.shopadminapi.product.domain.ProductComponent;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public interface FindAllProductComponentsUseCase extends Function<UUID, List<ProductComponent>> {
}
