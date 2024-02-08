package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.CreateComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.out.CreateComponentPort;
import com.gdas.shopadminapi.product.domain.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
class CreateComponentService implements CreateComponentUseCase {

    private final CreateComponentPort createComponentPort;

    CreateComponentService(CreateComponentPort createComponentPort) {
        this.createComponentPort = createComponentPort;
    }

    @Override
    public Component apply(Component component) {
        prepare(component);
        return createComponentPort.create(component);
    }

    private void prepare(Component component) {
        BigDecimal zero = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

        if (component.getMeasure().isMultiDimension()) {
            component.setBaseBuyAmount(zero);
            return;
        }

        component.setBaseBuyHeight(zero);
        component.setBaseBuyWidth(zero);
    }

}
