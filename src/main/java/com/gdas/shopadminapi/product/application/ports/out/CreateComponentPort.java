package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.Component;

public interface CreateComponentPort {
    Component create(Component component);
}
