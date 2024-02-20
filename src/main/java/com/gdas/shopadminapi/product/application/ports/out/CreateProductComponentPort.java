package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.ProductComponent;

public interface CreateProductComponentPort {
    ProductComponent create(ProductComponent productComponent);
}