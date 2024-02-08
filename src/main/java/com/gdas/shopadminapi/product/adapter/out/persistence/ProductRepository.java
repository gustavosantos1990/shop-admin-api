package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ProductRepository extends JpaRepository<Product, UUID> {
}
