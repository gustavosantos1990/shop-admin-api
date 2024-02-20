package com.gdas.shopadminapi.request.application.ports.in;

import com.gdas.shopadminapi.request.domain.Request;

import java.util.function.Function;

public interface CreateRequestUseCase extends Function<Request, Request> {
}
