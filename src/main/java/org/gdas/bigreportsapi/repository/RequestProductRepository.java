package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.RequestProduct;
import org.gdas.bigreportsapi.model.entity.RequestProductID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestProductRepository extends JpaRepository<RequestProduct, RequestProductID> {
    List<RequestProduct> findByRequestProductIDRequestId(Long id);
}
