package org.gdas.bigreportsapi.movement.application.port.in;

import org.gdas.bigreportsapi.movement.domain.Movement;

import java.util.function.Function;

public interface FindMovementByIDUseCase extends Function<Integer, Movement> {
}
