package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.entity.Customer;
import org.gdas.bigreportsapi.model.json.CustomerJSON;
import org.gdas.bigreportsapi.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Page<CustomerJSON>> get(
            Pageable pageable
    ) {
        Page<Customer> entities = customerService.findAll(pageable);
        Page<CustomerJSON> result = new PageImpl<>(
                entities.stream().map(CustomerJSON::from).collect(Collectors.toList()),
                entities.getPageable(),
                entities.getTotalElements());
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

}
