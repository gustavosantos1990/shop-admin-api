package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.Customer;

import java.util.Optional;

public interface FindCustomerByPhonePort {
    Optional<Customer> findByPhone(String phone);
}
