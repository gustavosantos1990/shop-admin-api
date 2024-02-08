package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.Product;

public interface CreateProductPort {
    Product create(Product product);
}
