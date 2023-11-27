package org.gdas.bigreportsapi.movement.application.port;

import org.gdas.bigreportsapi.movement.application.port.in.FindMovementsUseCase;
import org.gdas.bigreportsapi.movement.domain.Movement;

import java.util.Collections;
import java.util.List;

public class FindMovementsService implements FindMovementsUseCase {

    @Override
    public List<Movement> get() {
        return Collections.emptyList();
    }

}
