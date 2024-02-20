package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.CreateComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.out.CreateComponentPort;
import com.gdas.shopadminapi.product.domain.Component;
import org.springframework.stereotype.Service;

@Service
class CreateComponentService implements CreateComponentUseCase {

    private final CreateComponentPort createComponentPort;

    CreateComponentService(CreateComponentPort createComponentPort) {
        this.createComponentPort = createComponentPort;
    }

    @Override
    public Component apply(Component component) {
        return createComponentPort.create(component);
    }

}
