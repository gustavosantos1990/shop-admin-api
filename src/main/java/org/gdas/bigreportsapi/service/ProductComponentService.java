package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.repository.ProductComponentRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductComponentService {

    private final ProductComponentRepository productComponentRepository;

    public ProductComponentService(ProductComponentRepository productComponentRepository) {
        this.productComponentRepository = productComponentRepository;
    }

//    public ProductComponent findByID(UUID id) {
//        return productComponentRepository.findById(id)
//                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
//    }

    public ProductComponent save(ProductComponent entity) {
        return productComponentRepository.save(entity);
    }


}
