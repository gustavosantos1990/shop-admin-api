package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.application.ports.out.CreateComponentPort;
import com.gdas.shopadminapi.product.application.ports.out.FindAllComponentsPort;
import com.gdas.shopadminapi.product.application.ports.out.FindComponentByIdPort;
import com.gdas.shopadminapi.product.domain.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class ComponentPersistenceAdapter implements
        FindAllComponentsPort,
        FindComponentByIdPort,
        CreateComponentPort {

    private final ComponentRepository componentRepository;

    ComponentPersistenceAdapter(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @Override
    public List<Component> findAll() {
        return componentRepository.findAll();
    }

    @Override
    public Component create(Component component) {
        return componentRepository.saveAndFlush(component);
    }

    @Override
    public Optional<Component> findById(String id) {
        return componentRepository.findById(id);
    }
}
