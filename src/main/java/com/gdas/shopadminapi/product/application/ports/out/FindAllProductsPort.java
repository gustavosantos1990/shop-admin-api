package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.Product;

import java.util.List;

public interface FindAllProductsPort {
    List<Product> findAll();
}
