package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.RequestProduct;

public interface DeleteRequestProductPort {
    void delete(RequestProduct requestProduct);
}
