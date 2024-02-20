package com.gdas.shopadminapi.request.application.ports.in;

import com.gdas.shopadminapi.request.domain.Request;

import java.util.List;
import java.util.function.Supplier;

public interface FindAllRequestsUseCase extends Supplier<List<Request>> {
}
