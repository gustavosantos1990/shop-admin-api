package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComponentRepository extends JpaRepository<Component, String> {
    List<Component> findByDeletedAtIsNull();
}
