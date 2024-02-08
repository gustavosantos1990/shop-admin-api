package com.gdas.shopadminapi.product.application.ports.in;

import com.gdas.shopadminapi.product.domain.Component;

import java.util.List;
import java.util.function.Supplier;

public interface FindAllComponentsUseCase extends Supplier<List<Component>> {
}
