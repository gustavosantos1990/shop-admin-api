package org.gdas.bigreportsapi.controller;

import jakarta.validation.Valid;
import org.gdas.bigreportsapi.model.entity.Component;
import org.gdas.bigreportsapi.model.json.ComponentJSON;
import org.gdas.bigreportsapi.service.ComponentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/components")
@Validated
public class ComponentController {

    private final ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @GetMapping
    public ResponseEntity<Page<ComponentJSON>> get(Pageable pageable) {
        Page<Component> entities = componentService.findAll(pageable);
        Page<ComponentJSON> result = new PageImpl<>(
                entities.stream().map(ComponentJSON::from).collect(Collectors.toList()),
                entities.getPageable(),
                entities.getTotalElements());
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentJSON> getByID(@PathVariable UUID id) {
        Component entity = componentService.findByID(id);
        return ResponseEntity.ok(ComponentJSON.from(entity));
    }

    @PostMapping
    public ResponseEntity<ComponentJSON> post(@Valid @RequestBody ComponentJSON payload) {
        Component newEntity = Component.from(payload);
        Component saved = componentService.save(newEntity);
        return ResponseEntity.status(CREATED).body(ComponentJSON.from(saved));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ComponentJSON> patch(
            @PathVariable UUID id,
            @Valid @RequestBody ComponentJSON payload) {
        Component entity = Component.from(payload);
        Component updated = componentService.update(id, entity);
        return ResponseEntity.ok(ComponentJSON.from(updated));
    }

}
