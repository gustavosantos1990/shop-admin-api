package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.domain.Component;
import org.springframework.data.jpa.repository.JpaRepository;

interface ComponentRepository extends JpaRepository<Component, String> {
}
