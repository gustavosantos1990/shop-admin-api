package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.domain.ProductComponent;
import com.gdas.shopadminapi.product.domain.ProductComponentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface ProductComponentRepository extends JpaRepository<ProductComponent, ProductComponentId> {
    Page<ProductComponent> findByProductComponentIdComponentId(String componentId, Pageable pageable);
}
