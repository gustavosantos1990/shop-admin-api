package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.entity.Customer;
import org.gdas.bigreportsapi.model.json.CustomerJSON;
import org.gdas.bigreportsapi.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")
@Validated
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerJSON>> get() {
        List<Customer> entities = customerService.findAll();
        List<CustomerJSON> result = entities.stream().map(CustomerJSON::from).collect(Collectors.toList());
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @GetMapping("/{phone}")
    public ResponseEntity<CustomerJSON> get(@PathVariable String phone) {
        Customer entity = customerService.findByPhone(phone);
        return ResponseEntity.ok(CustomerJSON.from(entity));
    }

}
