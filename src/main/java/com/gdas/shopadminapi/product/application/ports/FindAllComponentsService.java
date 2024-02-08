package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.FindAllComponentsUseCase;
import com.gdas.shopadminapi.product.application.ports.out.FindAllComponentsPort;
import com.gdas.shopadminapi.product.domain.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class FindAllComponentsService implements FindAllComponentsUseCase {

    private final FindAllComponentsPort findAllComponentsPort;

    FindAllComponentsService(FindAllComponentsPort findAllComponentsPort) {
        this.findAllComponentsPort = findAllComponentsPort;
    }

    @Override
    public List<Component> get() {
        return findAllComponentsPort.findAll();
    }
}
