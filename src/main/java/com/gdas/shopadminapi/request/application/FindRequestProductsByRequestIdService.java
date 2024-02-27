package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.FindRequestProductsByRequestIdUseCase;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestProductsByRequestIdPort;
import com.gdas.shopadminapi.request.domain.RequestProduct;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FindRequestProductsByRequestIdService implements FindRequestProductsByRequestIdUseCase {

    private final FindRequestProductsByRequestIdPort findRequestProductsByRequestIdPort;

    FindRequestProductsByRequestIdService(FindRequestProductsByRequestIdPort findRequestProductsByRequestIdPort) {
        this.findRequestProductsByRequestIdPort = findRequestProductsByRequestIdPort;
    }

    @Override
    public List<RequestProduct> apply(Long requestId) {
        return findRequestProductsByRequestIdPort.findByRequestId(requestId);
    }
}
