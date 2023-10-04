package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Component;
import org.gdas.bigreportsapi.repository.ComponentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public Page<Component> findAll(Pageable pageable) {
        return componentRepository.findAll(pageable);
    }

    public Component findByID(UUID id) {
        return componentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }
    public Component save(Component entity) {
        return componentRepository.save(entity);
    }

    public Component update(UUID id, Component entity) {
        Component toUpdate = findByID(id);
        toUpdate.setName(entity.getName());
        toUpdate.setMeasure(entity.getMeasure());
        return componentRepository.save(toUpdate);
    }

}
