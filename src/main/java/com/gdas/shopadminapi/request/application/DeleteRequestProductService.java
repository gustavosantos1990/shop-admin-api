package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.DeleteRequestProductUseCase;
import com.gdas.shopadminapi.request.application.ports.out.DeleteRequestProductPort;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestProductByIdPort;
import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.RequestProductId;
import com.gdas.shopadminapi.request.domain.enummeration.RequestStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Service
class DeleteRequestProductService implements DeleteRequestProductUseCase {

    Logger logger = LoggerFactory.getLogger(DeleteRequestProductService.class);

    private final FindRequestProductByIdPort findRequestProductByIdPort;
    private final DeleteRequestProductPort deleteRequestProductPort;

    DeleteRequestProductService(FindRequestProductByIdPort findRequestProductByIdPort, DeleteRequestProductPort deleteRequestProductPort) {
        this.findRequestProductByIdPort = findRequestProductByIdPort;
        this.deleteRequestProductPort = deleteRequestProductPort;
    }

    @Override
    public void accept(RequestProductId requestProductId) {
        try {
            Optional<RequestProduct> optionalRequestProduct = findRequestProductByIdPort.findById(requestProductId);
            RequestProduct existingRequestProduct = optionalRequestProduct.orElseThrow(
                    () -> new ResponseStatusException(NOT_FOUND,
                            format("product of  id %s is not associated to request #%s",
                                    requestProductId.getProductId(), requestProductId.getRequestId()))
            );
            validateRequestStatus(existingRequestProduct);
            deleteRequestProductPort.delete(existingRequestProduct);
        } catch(Throwable t) {
            logger.error(t.getMessage(), t);
            throw new IllegalStateException(t);
        }
    }

    private void validateRequestStatus(RequestProduct existingRequestProduct) {
        if (!existingRequestProduct.getRequest().getStatus().equals(RequestStatus.CREATED)) {
            throw new ResponseStatusException(PRECONDITION_FAILED, format("request status must be CREATED, current status is %s",
                    existingRequestProduct.getRequest().getStatus()));
        }
    }

}
