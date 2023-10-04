package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.model.entity.ProductComponentID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductComponentRepository extends JpaRepository<ProductComponent, ProductComponentID> {}
