package com.gdas.shopadminapi.request.application.ports.out;

import com.gdas.shopadminapi.request.domain.Customer;

public interface SaveCustomerPort {
    Customer save(Customer customer);
}
