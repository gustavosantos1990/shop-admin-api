package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.domain.Customer;
import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
