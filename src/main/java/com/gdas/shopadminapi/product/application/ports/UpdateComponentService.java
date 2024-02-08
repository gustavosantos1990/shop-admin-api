package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.UpdateComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.out.CreateComponentPort;
import com.gdas.shopadminapi.product.application.ports.out.FindComponentByIdPort;
import com.gdas.shopadminapi.product.application.ports.out.FindProductComponentsByComponentIdPort;
import com.gdas.shopadminapi.product.domain.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Service
class UpdateComponentService implements UpdateComponentUseCase {

    private final CreateComponentPort createComponentPort;
    private final FindComponentByIdPort findComponentByIdPort;

    UpdateComponentService(CreateComponentPort createComponentPort, FindComponentByIdPort findComponentByIdPort, FindProductComponentsByComponentIdPort findProductComponentsByComponentIdPort) {
        this.createComponentPort = createComponentPort;
        this.findComponentByIdPort = findComponentByIdPort;
    }

    @Override
    public Component apply(String id, Component component) {
        Component existingComponent = getExistingComponent(id);
        updateProperties(existingComponent, component);
        return createComponentPort.create(existingComponent);
    }

    private void updateProperties(Component existingComponent, Component component) {

        if(!existingComponent.getMeasure().equals(component.getMeasure())){
            throw new ResponseStatusException(PRECONDITION_FAILED, "measure update is not allowed");
        }

        existingComponent.setBaseBuyPaidValue(component.getBaseBuyPaidValue());

        if (existingComponent.getMeasure().isMultiDimension()) {
            existingComponent.setBaseBuyWidth(component.getBaseBuyWidth());
            existingComponent.setBaseBuyHeight(component.getBaseBuyHeight());
            return;
        }

        existingComponent.setBaseBuyAmount(component.getBaseBuyAmount());
    }

    private Component getExistingComponent(String id) {
        return findComponentByIdPort.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid component ID (%s)", id)));
    }
}
