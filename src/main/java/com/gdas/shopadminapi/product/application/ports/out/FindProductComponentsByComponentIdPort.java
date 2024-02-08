package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.ProductComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindProductComponentsByComponentIdPort {
    Page<ProductComponent> findByComponentId(String componentId, Pageable pageable);
}
