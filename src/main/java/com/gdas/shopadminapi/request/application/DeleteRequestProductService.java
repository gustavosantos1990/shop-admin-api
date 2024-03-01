package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.request.application.ports.in.DeleteRequestProductUseCase;
import com.gdas.shopadminapi.request.application.ports.out.DeleteRequestProductPort;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestByIdPort;
import com.gdas.shopadminapi.request.domain.Request;
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

    private final FindRequestByIdPort findRequestByIdPort;
    private final DeleteRequestProductPort deleteRequestProductPort;

    DeleteRequestProductService(FindRequestByIdPort findRequestByIdPort, DeleteRequestProductPort deleteRequestProductPort) {
        this.findRequestByIdPort = findRequestByIdPort;
        this.deleteRequestProductPort = deleteRequestProductPort;
    }

    @Override
    public void accept(RequestProductId requestProductId) {
        try {
            Request request = findRequestByIdPort.findById(requestProductId.getRequestId()).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid request ID (%s)",
                    requestProductId.getRequestId())));

            Optional<RequestProduct> optionalRequestProduct = request.getRequestProducts()
                    .stream()
                    .filter(rp -> rp.getProduct().getId().equals(requestProductId.getProductId()))
                    .findFirst();

            RequestProduct existingRequestProduct = optionalRequestProduct.orElseThrow(() ->
                    new ResponseStatusException(NOT_FOUND, format("product of  id %s is not associated to request #%s",
                            requestProductId.getProductId(), requestProductId.getRequestId())));

            validate(request, existingRequestProduct);

            deleteRequestProductPort.delete(existingRequestProduct);
        } catch(Throwable t) {
            logger.error(t.getMessage(), t);
            throw new IllegalStateException(t);
        }
    }

    private void validate(Request request, RequestProduct existingRequestProduct) {
        if (RequestStatus.DELIVERED.getSequence() <= existingRequestProduct.getRequest().getStatus().getSequence()) {
            throw new ResponseStatusException(PRECONDITION_FAILED, format("can't delete products of requests in %s status",
                    existingRequestProduct.getRequest().getStatus()));
        }

        if (request.getRequestProducts().size() == 1) {
            throw new ResponseStatusException(PRECONDITION_FAILED, "request must have at least one product");
        }
    }

}
