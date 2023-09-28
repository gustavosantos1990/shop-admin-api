package org.gdas.bigreportsapi.service;

import org.gdas.bigreportsapi.model.entity.ProductComponent;
import org.gdas.bigreportsapi.repository.ProductsComponentsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsComponentsService {

    private static final int PAGE_SIZE = 10;
    private final ProductsComponentsRepository productsComponentsRepository;

    public ProductsComponentsService(ProductsComponentsRepository productsComponentsRepository) {
        this.productsComponentsRepository = productsComponentsRepository;
    }

    public List<ProductComponent> findAll() {
        return productsComponentsRepository.findAll();
    }

}
