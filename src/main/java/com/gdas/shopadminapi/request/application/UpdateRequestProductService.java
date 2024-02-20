package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.UpdateRequestProductUseCase;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestProductByIdPort;
import com.gdas.shopadminapi.request.application.ports.out.SaveRequestProductPort;
import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.enummeration.RequestStatus;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@Service
class UpdateRequestProductService implements UpdateRequestProductUseCase {

    Logger logger = LoggerFactory.getLogger(UpdateRequestProductService.class);

    private final FindRequestProductByIdPort findRequestProductByIdPort;
    private final SaveRequestProductPort saveRequestProductPort;

    UpdateRequestProductService(FindRequestProductByIdPort findRequestProductByIdPort, SaveRequestProductPort saveRequestProductPort) {
        this.findRequestProductByIdPort = findRequestProductByIdPort;
        this.saveRequestProductPort = saveRequestProductPort;
    }

    @Override
    @Transactional
    public RequestProduct apply(RequestProduct requestProduct) {
        try {
            Optional<RequestProduct> optionalRequestProduct = findRequestProductByIdPort.findById(requestProduct.getRequestProductId());
            RequestProduct existingRequestProduct = optionalRequestProduct.orElseThrow(
                    () -> new ResponseStatusException(NOT_FOUND,
                            format("product of  id %s is not associated to request #%s",
                                    requestProduct.getRequestProductId().getProductId(),
                                    requestProduct.getRequestProductId().getRequestId()))
            );

            validateRequestStatus(existingRequestProduct);

            copyProperties(existingRequestProduct, requestProduct);

            return saveRequestProductPort.save(existingRequestProduct);
        } catch(Throwable t) {
            logger.error(t.getMessage(), t);
            throw new IllegalStateException(t);
        }
    }

    private void copyProperties(RequestProduct existingRequestProduct, RequestProduct requestProduct) {
        existingRequestProduct.setDeclaredProductionCost(requestProduct.getDeclaredProductionCost());
        existingRequestProduct.setUnitaryValue(requestProduct.getUnitaryValue());
        existingRequestProduct.setAmount(requestProduct.getAmount());
        existingRequestProduct.setNotes(requestProduct.getNotes());
        existingRequestProduct.setFilePath(requestProduct.getFilePath());
        existingRequestProduct.setFileLink(requestProduct.getFileLink());
    }

    private void validateRequestStatus(RequestProduct existingRequestProduct) {
        if (!existingRequestProduct.getRequest().getStatus().equals(RequestStatus.CREATED)) {
            throw new ResponseStatusException(PRECONDITION_FAILED, format("request status must be CREATED, current status is %s",
                    existingRequestProduct.getRequest().getStatus()));
        }
    }
}
