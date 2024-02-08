package com.gdas.shopadminapi.product.application.ports.in;

import com.gdas.shopadminapi.product.domain.Component;

import java.util.function.BiFunction;
import java.util.function.Function;

public interface UpdateComponentUseCase extends BiFunction<String, Component, Component> {
}
