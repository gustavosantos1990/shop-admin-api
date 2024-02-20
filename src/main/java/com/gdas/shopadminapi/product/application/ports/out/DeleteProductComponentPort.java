package com.gdas.shopadminapi.product.application.ports.out;

import java.util.UUID;

public interface DeleteProductComponentPort {
    int delete(UUID productId, String componentId);
}