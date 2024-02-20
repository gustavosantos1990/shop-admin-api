package com.gdas.shopadminapi.request.application.ports.in;

import com.gdas.shopadminapi.request.domain.RequestProduct;

import java.util.function.Function;

public interface CreateRequestProductUseCase extends Function<RequestProduct, RequestProduct> {
}
