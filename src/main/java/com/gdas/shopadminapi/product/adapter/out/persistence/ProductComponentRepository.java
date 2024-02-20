package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.domain.ProductComponent;
import com.gdas.shopadminapi.product.domain.ProductComponentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

interface ProductComponentRepository extends JpaRepository<ProductComponent, ProductComponentId> {
    Optional<ProductComponent> findByProductComponentIdProductIdAndProductComponentIdComponentId(UUID productId, String componentId);
    Page<ProductComponent> findByProductComponentIdProductId(UUID productId, Pageable pageable);
    Page<ProductComponent> findByProductComponentIdComponentId(String componentId, Pageable pageable);
    int deleteByProductComponentIdProductIdAndProductComponentIdComponentId(UUID productId, String componentId);
}
