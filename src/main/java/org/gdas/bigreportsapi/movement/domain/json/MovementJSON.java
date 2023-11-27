package org.gdas.bigreportsapi.movement.domain.json;

import org.gdas.bigreportsapi.movement.domain.Movement;

public class MovementJSON {
    public static MovementJSON from(Movement movement) {
        return new MovementJSON();
    }
}
