package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.FindAllProductsUseCase;
import com.gdas.shopadminapi.product.application.ports.out.FindAllProductsPort;
import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FindAllProductsService implements FindAllProductsUseCase {

    private final FindAllProductsPort findAllProductsPort;

    FindAllProductsService(FindAllProductsPort findAllProductsPort) {
        this.findAllProductsPort = findAllProductsPort;
    }

    @Override
    public List<Product> get() {
        return findAllProductsPort.findAll();
    }
}
