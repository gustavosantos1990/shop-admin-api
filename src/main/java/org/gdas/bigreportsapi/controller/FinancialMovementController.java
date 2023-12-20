package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.entity.FinancialMovement;
import org.gdas.bigreportsapi.service.FinancialMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/financial_movements")
@Validated
public class FinancialMovementController {

    private final FinancialMovementService financialMovementService;

    public FinancialMovementController(FinancialMovementService financialMovementService) {
        this.financialMovementService = financialMovementService;
    }

    @GetMapping
    public ResponseEntity<List<FinancialMovement>> get() {
        List<FinancialMovement> entities = financialMovementService.findAll();
        entities.forEach(fm -> fm.getRequest().setRequestProducts(null));
        return ResponseEntity.ok(entities);
    }

    @PostMapping
    public ResponseEntity<FinancialMovement> post(@RequestBody FinancialMovement payload) {
        FinancialMovement saved = financialMovementService.save(payload);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(format("/%s", saved.getId()))
                .buildAndExpand(saved)
                .toUri();
        return ResponseEntity
                .created(uri)
                .body(saved);
    }

}
