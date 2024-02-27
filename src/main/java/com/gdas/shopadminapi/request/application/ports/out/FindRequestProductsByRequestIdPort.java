package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.RequestProduct;

import java.util.List;

public interface FindRequestProductsByRequestIdPort {
    List<RequestProduct> findByRequestId(Long requestId);
}
