package com.gdas.shopadminapi.product.adapter.in.web;

import com.gdas.shopadminapi.product.application.ports.in.CreateComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.in.FindAllComponentsUseCase;
import com.gdas.shopadminapi.product.application.ports.in.FindComponentByIdUseCase;
import com.gdas.shopadminapi.product.application.ports.in.UpdateComponentUseCase;
import com.gdas.shopadminapi.product.domain.Component;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/components")
public class ComponentController {

    private final FindAllComponentsUseCase findAllComponentsUseCase;
    private final FindComponentByIdUseCase findComponentByIdUseCase;
    private final CreateComponentUseCase createComponentUseCase;
    private final UpdateComponentUseCase updateComponentUseCase;

    public ComponentController(FindAllComponentsUseCase findAllComponentsUseCase, FindComponentByIdUseCase findComponentByIdUseCase, CreateComponentUseCase createComponentUseCase, UpdateComponentUseCase updateComponentUseCase) {
        this.findAllComponentsUseCase = findAllComponentsUseCase;
        this.findComponentByIdUseCase = findComponentByIdUseCase;
        this.createComponentUseCase = createComponentUseCase;
        this.updateComponentUseCase = updateComponentUseCase;
    }

    @GetMapping
    private ResponseEntity<List<Component>> findAllComponents() {
        List<Component> components = findAllComponentsUseCase.get();
        return ResponseEntity.ok(components);
    }

    @GetMapping("{id}")
    private ResponseEntity<Component> findById(@PathVariable String id) {
        Component components = findComponentByIdUseCase.apply(id);
        return ResponseEntity.ok(components);
    }

    @PostMapping
    private ResponseEntity<Component> create(@Validated(CreateComponentUseCase.class) @RequestBody Component component) {
        Component newComponent = createComponentUseCase.apply(component);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(format("/%s", newComponent.getId()))
                .buildAndExpand(newComponent)
                .toUri();
        return ResponseEntity.created(uri).body(newComponent);
    }

    @PatchMapping("{id}")
    private ResponseEntity<Component> update(
            @PathVariable String id,
            @Validated(UpdateComponentUseCase.class) @RequestBody Component component) {
        Component newComponent = updateComponentUseCase.apply(id, component);
        return ResponseEntity.ok(newComponent);
    }

}
