package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor {

    @Query(value = "SELECT * FROM product", nativeQuery = true)
    List<Product> findProductsOnly();

}
