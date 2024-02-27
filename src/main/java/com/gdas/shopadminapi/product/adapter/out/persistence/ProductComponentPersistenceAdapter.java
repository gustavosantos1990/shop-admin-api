package com.gdas.shopadminapi.product.adapter.out.persistence;

import com.gdas.shopadminapi.product.application.ports.out.*;
import com.gdas.shopadminapi.product.domain.Component;
import com.gdas.shopadminapi.product.domain.Product;
import com.gdas.shopadminapi.product.domain.ProductComponent;
import com.gdas.shopadminapi.product.domain.ProductComponentId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
class ProductComponentPersistenceAdapter implements
        FindProductComponentsByProductIdPort,
        FindProductComponentsByComponentIdPort,
        CreateProductComponentPort,
        UpdateProductComponentPort,
        FindProductComponentByIdPort,
        DeleteProductComponentPort
{

    private final ProductComponentRepository productComponentRepository;


    ProductComponentPersistenceAdapter(ProductComponentRepository productComponentRepository) {
        this.productComponentRepository = productComponentRepository;
    }

    @Override
    public Page<ProductComponent> findByProductId(UUID productId, Pageable pageable) {
        return productComponentRepository.selectByProductIdFetchComponents(productId, pageable);
    }

    @Override
    public Page<ProductComponent> findByComponentId(String componentId, Pageable pageable) {
        return productComponentRepository.findByProductComponentIdComponentId(componentId, pageable);
    }

    @Override
    public ProductComponent create(ProductComponent productComponent) {
        return productComponentRepository.save(productComponent);
    }

    @Override
    public ProductComponent update(ProductComponent productComponent) {
        return productComponentRepository.save(productComponent);
    }

    @Override
    public Optional<ProductComponent> findById(ProductComponentId productComponentId) {
        return productComponentRepository.findByProductComponentIdProductIdAndProductComponentIdComponentId(
                productComponentId.getProductId(),
                productComponentId.getComponentId());
    }

    @Override
    public int delete(UUID productId, String componentId) {
        return productComponentRepository.deleteByProductComponentIdProductIdAndProductComponentIdComponentId(productId, componentId);
    }
}
