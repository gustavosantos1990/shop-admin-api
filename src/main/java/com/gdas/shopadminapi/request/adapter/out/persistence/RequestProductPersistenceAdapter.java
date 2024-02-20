package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.application.ports.out.DeleteRequestProductPort;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestProductByIdPort;
import com.gdas.shopadminapi.request.application.ports.out.SaveRequestProductPort;
import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.RequestProductId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
class RequestProductPersistenceAdapter implements
        FindRequestProductByIdPort,
        SaveRequestProductPort,
        DeleteRequestProductPort {

    private final RequestProductRepository requestProductRepository;

    RequestProductPersistenceAdapter(RequestProductRepository requestProductRepository) {
        this.requestProductRepository = requestProductRepository;
    }

    @Override
    public RequestProduct save(RequestProduct requestProduct) {
        return requestProductRepository.save(requestProduct);
    }

    @Override
    public Optional<RequestProduct> findById(RequestProductId requestProductId) {
        return requestProductRepository.findById(requestProductId);
    }

    @Override
    public void delete(RequestProduct requestProduct) {
        requestProductRepository.delete(requestProduct);
    }
}
