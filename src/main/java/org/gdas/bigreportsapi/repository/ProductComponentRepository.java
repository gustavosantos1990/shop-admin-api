package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductComponentRepository extends JpaRepository<ProductComponent, UUID> {}
