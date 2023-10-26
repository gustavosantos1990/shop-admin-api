package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.RequestProduct;
import org.gdas.bigreportsapi.model.entity.RequestProductID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RequestProductRepository extends JpaRepository<RequestProduct, RequestProductID> {
    List<RequestProduct> findByRequestProductIDRequestId(Long requestID);
    Optional<RequestProduct> findByRequestProductIDRequestIdAndRequestProductIDProductId(Long requestID, UUID productID);
}
