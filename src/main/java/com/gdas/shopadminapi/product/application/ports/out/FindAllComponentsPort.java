package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.Component;

import java.util.List;

public interface FindAllComponentsPort {
    List<Component> findAll();
}
