package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.Component;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComponentRepository extends JpaRepository<Component, UUID> {}
