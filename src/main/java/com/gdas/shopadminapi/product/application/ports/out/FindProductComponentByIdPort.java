package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.ProductComponent;
import com.gdas.shopadminapi.product.domain.ProductComponentId;

import java.util.Optional;

public interface FindProductComponentByIdPort {
    Optional<ProductComponent> findById(ProductComponentId productComponentId);
}
