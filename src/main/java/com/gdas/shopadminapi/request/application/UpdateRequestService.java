package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.UpdateRequestUseCase;
import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.stereotype.Service;

@Service
class UpdateRequestService implements UpdateRequestUseCase {

    @Override
    public Request apply(Request request) {
        return request;
    }

}
