package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.application.ports.out.CreateComponentPort;
import com.gdas.shopadminapi.product.application.ports.out.FindAllComponentsPort;
import com.gdas.shopadminapi.product.application.ports.out.FindComponentByIdPort;
import com.gdas.shopadminapi.product.application.ports.out.FindProductComponentsByComponentIdPort;
import com.gdas.shopadminapi.product.domain.Component;
import com.gdas.shopadminapi.product.domain.ProductComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class ProductComponentPersistenceAdapter implements FindProductComponentsByComponentIdPort {

    private final ProductComponentRepository productComponentRepository;


    ProductComponentPersistenceAdapter(ProductComponentRepository productComponentRepository) {
        this.productComponentRepository = productComponentRepository;
    }

    @Override
    public Page<ProductComponent> findByComponentId(String componentId, Pageable pageable) {
        return productComponentRepository.findByProductComponentIdComponentId(componentId, pageable);
    }
}
