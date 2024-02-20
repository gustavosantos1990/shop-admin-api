package com.gdas.shopadminapi.request.adapter.out.persistence;

import com.gdas.shopadminapi.request.application.ports.out.FindCustomerByIdPort;
import com.gdas.shopadminapi.request.application.ports.out.FindCustomerByPhonePort;
import com.gdas.shopadminapi.request.application.ports.out.SaveCustomerPort;
import com.gdas.shopadminapi.request.domain.Customer;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
class CustomerPersistenceAdapter implements
        FindCustomerByIdPort,
        FindCustomerByPhonePort,
        SaveCustomerPort {

    private final CustomerRepository customerRepository;

    CustomerPersistenceAdapter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> findByPhone(String phone) {
        Example<Customer> example = exampleFromPhone(phone);
        return customerRepository.findOne(example);
    }

    @Override
    public Optional<Customer> findById(UUID customerId) {
        return customerRepository.findById(customerId);
    }

    private Example<Customer> exampleFromPhone(String phone) {
        Customer customer = new Customer();
        customer.setPhone(phone);
        return Example.of(customer);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
