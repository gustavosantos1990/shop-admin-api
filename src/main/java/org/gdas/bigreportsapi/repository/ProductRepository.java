package org.gdas.bigreportsapi.repository;

import org.gdas.bigreportsapi.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findByReady(boolean ready, Pageable pageable);

}
