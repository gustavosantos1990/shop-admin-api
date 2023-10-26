package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.model.entity.ProductComponentID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductComponentRepository extends JpaRepository<ProductComponent, ProductComponentID> {
    List<ProductComponent> findByProductComponentIDProductId(UUID id);
    Optional<ProductComponent> findByProductComponentIDProductIdAndProductComponentIDComponentId(UUID productID, String componentID);
}
