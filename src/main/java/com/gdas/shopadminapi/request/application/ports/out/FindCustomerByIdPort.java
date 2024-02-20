package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.Customer;

import java.util.Optional;
import java.util.UUID;

public interface FindCustomerByIdPort {
    Optional<Customer> findById(UUID customerId);
}
