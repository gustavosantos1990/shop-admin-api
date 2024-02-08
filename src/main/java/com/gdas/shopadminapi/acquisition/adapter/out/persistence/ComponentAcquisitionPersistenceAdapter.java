package com.gdas.shopadminapi.acquisition.adapter.out.persistence;

import com.gdas.shopadminapi.acquisition.domain.ComponentAcquisition;
import com.gdas.shopadminapi.product.application.ports.out.CreateComponentAcquisitionPort;
import org.springframework.stereotype.Service;

@Service
class ComponentAcquisitionPersistenceAdapter implements CreateComponentAcquisitionPort {

    private final ComponentAcquisitionRepository componentAcquisitionRepository;

    ComponentAcquisitionPersistenceAdapter(ComponentAcquisitionRepository componentAcquisitionRepository) {
        this.componentAcquisitionRepository = componentAcquisitionRepository;
    }

    @Override
    public ComponentAcquisition create(ComponentAcquisition componentAcquisition) {
        return componentAcquisitionRepository.save(componentAcquisition);
    }

}
