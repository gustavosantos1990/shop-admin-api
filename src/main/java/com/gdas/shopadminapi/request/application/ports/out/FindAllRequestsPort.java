package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.Request;

import java.util.List;

public interface FindAllRequestsPort {
    List<Request> findAll();
}
