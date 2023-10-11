package org.gdas.bigreportsapi.service;

import jakarta.transaction.Transactional;
import org.gdas.bigreportsapi.model.entity.Customer;
import org.gdas.bigreportsapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Customer findByPhone(String phone) {
        return customerRepository.findByPhone(phone)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid phone"));
    }

    public Customer save(Customer entity) {
        return customerRepository.save(entity);
    }

    @Transactional
    public Customer selectSave(Customer customer) {
        if (customer.getId() != null) {
            Optional<Customer> optCustomer = customerRepository.findById(customer.getId());
            Customer toSave = optCustomer.orElse(customer);
            toSave.setName(customer.getName());
            return customerRepository.save(toSave);
        }

        Optional<Customer> optCustomer = customerRepository.findByPhone(customer.getPhone());
        Customer toSave = optCustomer.orElse(customer);
        toSave.setName(customer.getName());
        return customerRepository.save(toSave);
    }
}
