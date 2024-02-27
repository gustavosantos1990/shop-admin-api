package com.gdas.shopadminapi.request.application.ports.in;

import com.gdas.shopadminapi.request.domain.RequestProduct;

import java.util.List;
import java.util.function.Function;

public interface FindRequestProductsByRequestIdUseCase extends Function<Long, List<RequestProduct>> {}
