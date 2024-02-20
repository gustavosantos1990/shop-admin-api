package com.gdas.shopadminapi.request.application;

import com.gdas.shopadminapi.product.application.ports.out.FindProductByIdPort;
import com.gdas.shopadminapi.product.domain.Product;
import com.gdas.shopadminapi.request.application.ports.in.CreateRequestProductUseCase;
import com.gdas.shopadminapi.request.application.ports.out.CreateRequestProductPort;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestByIdPort;
import com.gdas.shopadminapi.request.application.ports.out.FindRequestProductByIdPort;
import com.gdas.shopadminapi.request.domain.Request;
import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.RequestProductDocument;
import com.gdas.shopadminapi.request.domain.RequestProductId;
import com.gdas.shopadminapi.request.domain.enummeration.RequestStatus;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.*;

@Service
class CreateRequestProductService implements CreateRequestProductUseCase {

    Logger logger = LoggerFactory.getLogger(CreateRequestProductService.class);

    private final FindRequestProductByIdPort findRequestProductByIdPort;
    private final CreateRequestProductPort createRequestProductPort;
    private final FindRequestByIdPort findRequestByIdPort;
    private final FindProductByIdPort findProductByIdPort;

    CreateRequestProductService(FindRequestProductByIdPort findRequestProductByIdPort, CreateRequestProductPort createRequestProductPort, FindRequestByIdPort findRequestByIdPort, FindProductByIdPort findProductByIdPort) {
        this.findRequestProductByIdPort = findRequestProductByIdPort;
        this.createRequestProductPort = createRequestProductPort;
        this.findRequestByIdPort = findRequestByIdPort;
        this.findProductByIdPort = findProductByIdPort;
    }

    @Override
    @Transactional
    public RequestProduct apply(RequestProduct requestProduct) {
        try {
            Optional<RequestProduct> optionalRequestProduct = findRequestProductByIdPort.findById(requestProduct.getRequestProductId());
            optionalRequestProduct.ifPresent(rp -> {
                throw new ResponseStatusException(UNPROCESSABLE_ENTITY,
                    format("product %s is already associated with request #%s",
                            rp.getProduct().getName(),
                            rp.getRequest().getId()));
            });

            CompletableFuture<Optional<Request>> futureRequest = CompletableFuture.supplyAsync(() -> findRequestByIdPort.findById(requestProduct.getRequestProductId().getRequestId()));
            CompletableFuture<Optional<Product>> futureProduct = CompletableFuture.supplyAsync(() -> findProductByIdPort.findById(requestProduct.getRequestProductId().getProductId()));

            CompletableFuture.allOf(futureRequest, futureProduct);

            Request request = futureRequest.get().orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid request ID (%s)",
                    requestProduct.getRequestProductId().getRequestId())));
            Product product = futureProduct.get().orElseThrow(() -> new ResponseStatusException(NOT_FOUND, format("Invalid product ID (%s)",
                    requestProduct.getRequestProductId().getProductId())));

            if (!request.getStatus().equals(RequestStatus.CREATED)) {
                throw new ResponseStatusException(PRECONDITION_FAILED,
                        format("request status must be CREATED, current status is %s", request.getStatus()));
            }

            requestProduct.setRequestProductId(new RequestProductId(request.getId(), product.getId()));
            requestProduct.setRequest(request);
            requestProduct.setProduct(product);
            requestProduct.setCalculatedProductionCost(product.productionCost());
            requestProduct.setProductDocument(RequestProductDocument.fromProduct(product));

            return createRequestProductPort.create(requestProduct);
        } catch(Throwable t) {
            logger.error(t.getMessage(), t);
            throw new IllegalStateException(t);
        }
    }
}
