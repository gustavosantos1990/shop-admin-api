package org.gdas.bigreportsapi.service;

import jakarta.transaction.Transactional;
import org.gdas.bigreportsapi.model.entity.Customer;
import org.gdas.bigreportsapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.StringUtils.isEmpty;
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
    public Customer selectSave(Customer payloadCustomer) {
        if (payloadCustomer.getId() != null) {
            Optional<Customer> optCustomer = customerRepository.findById(payloadCustomer.getId());
            Customer toSave = optCustomer.orElse(payloadCustomer);
            copyProperties(toSave, payloadCustomer);
            return customerRepository.save(toSave);
        }

        Optional<Customer> optCustomer = customerRepository.findByPhone(payloadCustomer.getPhone());
        Customer toSave = optCustomer.orElse(payloadCustomer);
        copyProperties(toSave, payloadCustomer);
        return customerRepository.save(toSave);
    }

    private void copyProperties(Customer target, Customer source) {
        target.setName(source.getName());
        if (!isEmpty(source.getFacebookChatNumber())) {
            target.setFacebookChatNumber(source.getFacebookChatNumber());
        }
    }
}
