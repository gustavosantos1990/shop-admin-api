package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.Customer;
import org.gdas.bigreportsapi.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    public Customer findByPhone(String phone) {
        return customerRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid phone"));
    }

    public Customer save(Customer entity) {
        return customerRepository.save(entity);
    }

    public Customer selectSave(Customer customer) {
        if (customer.getId() != null) {
            Optional<Customer> optCustomer = customerRepository.findById(customer.getId());

            if (optCustomer.isPresent()) return optCustomer.get();

            return customerRepository.save(customer);
        }

        Optional<Customer> optCustomer = customerRepository.findByPhone(customer.getPhone());

        if (optCustomer.isPresent()) return optCustomer.get();

        return customerRepository.save(customer);

    }
}
