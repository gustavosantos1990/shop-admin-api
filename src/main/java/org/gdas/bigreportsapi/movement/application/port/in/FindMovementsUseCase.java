package org.gdas.bigreportsapi.movement.application.port.in;

import org.gdas.bigreportsapi.movement.domain.Movement;

import java.util.List;
import java.util.function.Supplier;

public interface FindMovementsUseCase extends Supplier<List<Movement>> {
}
