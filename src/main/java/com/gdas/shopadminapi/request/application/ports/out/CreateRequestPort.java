package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.Request;

public interface CreateRequestPort {
    Request create(Request request);
}
