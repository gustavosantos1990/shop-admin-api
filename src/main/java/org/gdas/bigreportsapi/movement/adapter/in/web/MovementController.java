package org.gdas.bigreportsapi.movement.adapter.in.web;

import org.gdas.bigreportsapi.movement.application.port.in.FindMovementByIDUseCase;
import org.gdas.bigreportsapi.movement.application.port.in.FindMovementsUseCase;
import org.gdas.bigreportsapi.movement.domain.Movement;
import org.gdas.bigreportsapi.movement.domain.json.MovementJSON;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/movements")
@Validated
public class MovementController {

    private final FindMovementsUseCase findMovementsUseCase;
    private final FindMovementByIDUseCase findMovementByIDUseCase;

    public MovementController(FindMovementsUseCase findMovementsUseCase, FindMovementByIDUseCase findMovementByIDUseCase) {
        this.findMovementsUseCase = findMovementsUseCase;
        this.findMovementByIDUseCase = findMovementByIDUseCase;
    }

    @GetMapping
    public ResponseEntity<List<MovementJSON>> get() {
        List<Movement> entities = findMovementsUseCase.get();
        List<MovementJSON> result = entities.stream().map(MovementJSON::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementJSON> getByID(@PathVariable Integer id) {
        Movement movement = findMovementByIDUseCase.apply(id);
        return ResponseEntity.ok(MovementJSON.from(movement));
    }

}
