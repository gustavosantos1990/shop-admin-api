package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.RequestProductId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface RequestProductRepository extends JpaRepository<RequestProduct, RequestProductId> {
    List<RequestProduct> findByRequestId(Long requestId);
}
