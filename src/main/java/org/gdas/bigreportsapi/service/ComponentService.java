package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Component;
import org.gdas.bigreportsapi.repository.ComponentsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ComponentService {

    private static final int PAGE_SIZE = 10;
    private final ComponentsRepository componentsRepository;

    public ComponentService(ComponentsRepository componentsRepository) {
        this.componentsRepository = componentsRepository;
    }

    public Page<Component> findAll(Pageable pageable) {
        return componentsRepository.findAll(pageable);
    }

    public Component findByID(UUID id) {
        return componentsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }
    public Component save(Component entity) {
        return componentsRepository.save(entity);
    }

    public Component update(UUID id, Component entity) {
        Component toUpdate = findByID(id);
        toUpdate.setName(entity.getName());
        toUpdate.setMeasure(entity.getMeasure());
        return componentsRepository.save(toUpdate);
    }

}
