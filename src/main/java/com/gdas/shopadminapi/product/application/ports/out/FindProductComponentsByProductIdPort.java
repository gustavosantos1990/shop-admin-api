package com.gdas.shopadminapi.product.application.ports.out;

import com.gdas.shopadminapi.product.domain.ProductComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FindProductComponentsByProductIdPort {
    Page<ProductComponent> findByProductId(UUID productId, Pageable pageable);
}
