package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.application.ports.out.CreateRequestProductPort;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestProductByIdPort;
import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.RequestProductId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class RequestProductPersistenceAdapter implements
        FindRequestProductByIdPort,
        CreateRequestProductPort {

    private final RequestProductRepository requestProductRepository;

    RequestProductPersistenceAdapter(RequestProductRepository requestProductRepository) {
        this.requestProductRepository = requestProductRepository;
    }

    @Override
    public RequestProduct create(RequestProduct requestProduct) {
        return requestProductRepository.save(requestProduct);
    }

    @Override
    public Optional<RequestProduct> findById(RequestProductId requestProductId) {
        return requestProductRepository.findById(requestProductId);
    }
}
