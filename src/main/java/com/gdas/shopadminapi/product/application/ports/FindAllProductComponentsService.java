package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.FindAllProductComponentsUseCase;
import com.gdas.shopadminapi.product.application.ports.out.FindProductComponentsByProductIdPort;
import com.gdas.shopadminapi.product.domain.ProductComponent;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
class FindAllProductComponentsService implements FindAllProductComponentsUseCase {

    private final FindProductComponentsByProductIdPort findProductComponentsByProductIdPort;


    FindAllProductComponentsService(FindProductComponentsByProductIdPort findProductComponentsByProductIdPort) {
        this.findProductComponentsByProductIdPort = findProductComponentsByProductIdPort;
    }

    @Override
    public List<ProductComponent> apply(UUID productId) {
        return findProductComponentsByProductIdPort.findByProductId(productId, Pageable.unpaged()).getContent();
    }
}
