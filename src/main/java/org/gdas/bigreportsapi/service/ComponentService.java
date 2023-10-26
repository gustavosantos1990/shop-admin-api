package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Component;
import org.gdas.bigreportsapi.repository.ComponentRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ComponentService {

    private final ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public List<Component> findAll(boolean includeDeleted) {
        return includeDeleted ? componentRepository.findAll() : componentRepository.findByDeletedAtIsNull();
    }

    public Component findByID(String id) {
        return componentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }
    public Component save(Component entity) {
        if (entity.getMeasure().isMultiDimension()) {
            BigDecimal baseBuyAmount = calculateAmount(entity);
            entity.setBaseBuyAmount(baseBuyAmount.setScale(2, RoundingMode.HALF_UP));
        } else {
            entity.setBaseBuyWidth(ZERO);
            entity.setBaseBuyHeight(ZERO);
        }
        return componentRepository.save(entity);
    }

    public Component update(String id, Component entity) {
        Component toUpdate = findByID(id);
        if (toUpdate.getMeasure().isMultiDimension()) {
            BigDecimal baseBuyAmount = calculateAmount(entity);
            toUpdate.setBaseBuyAmount(baseBuyAmount);
            toUpdate.setBaseBuyHeight(entity.getBaseBuyHeight());
            toUpdate.setBaseBuyWidth(entity.getBaseBuyWidth());
        } else {
            toUpdate.setBaseBuyAmount(entity.getBaseBuyAmount());
        }
        toUpdate.setBaseBuyPaidValue(entity.getBaseBuyPaidValue());
        return componentRepository.save(toUpdate);
    }

    private BigDecimal calculateAmount(Component component) {
        BigDecimal baseBuyAmount = component.getBaseBuyHeight().multiply(component.getBaseBuyWidth());
        return baseBuyAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public Component delete(String id) {
        Component component = findByID(id);
        component.setDeletedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return componentRepository.save(component);
    }

}
