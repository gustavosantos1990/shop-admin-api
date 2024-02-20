package com.gdas.shopadminapi.product.application.ports.in;

import java.util.UUID;
import java.util.function.BiConsumer;

public interface DeleteProductComponentUseCase extends BiConsumer<UUID, String> {
}
