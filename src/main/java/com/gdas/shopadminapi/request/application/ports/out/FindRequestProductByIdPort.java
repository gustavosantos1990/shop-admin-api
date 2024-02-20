package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.RequestProductId;

import java.util.Optional;

public interface FindRequestProductByIdPort {
    Optional<RequestProduct> findById(RequestProductId requestProductId);
}
