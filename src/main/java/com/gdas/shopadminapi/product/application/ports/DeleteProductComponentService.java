package com.gdas.shopadminapi.product.application.ports;

import com.gdas.shopadminapi.product.application.ports.in.DeleteProductComponentUseCase;
import com.gdas.shopadminapi.product.application.ports.out.DeleteProductComponentPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
class DeleteProductComponentService implements DeleteProductComponentUseCase {

    private final DeleteProductComponentPort deleteProductComponentPort;

    DeleteProductComponentService(DeleteProductComponentPort deleteProductComponentPort) {
        this.deleteProductComponentPort = deleteProductComponentPort;
    }

    @Override
    @Transactional
    public void accept(UUID productId, String componentId) {
        int amountOfDeletedResults = deleteProductComponentPort.delete(productId, componentId);
        if (amountOfDeletedResults == 0) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, format("there is not existing combination of product and component ids: %s/%s", productId, componentId));
        }
    }

}
