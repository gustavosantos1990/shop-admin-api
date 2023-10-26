package org.gdas.bigreportsapi.service;

import jakarta.transaction.Transactional;
import org.gdas.bigreportsapi.model.entity.Product;
import org.gdas.bigreportsapi.model.entity.Request;
import org.gdas.bigreportsapi.model.entity.RequestProduct;
import org.gdas.bigreportsapi.model.entity.RequestProductID;
import org.gdas.bigreportsapi.model.enummeration.RequestStatus;
import org.gdas.bigreportsapi.repository.RequestProductRepository;
import org.gdas.bigreportsapi.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@Transactional
public class RequestService {

    private final RequestRepository requestRepository;
    private final RequestProductRepository requestProductRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public RequestService(RequestRepository requestRepository, RequestProductRepository requestProductRepository, CustomerService customerService, ProductService productService) {
        this.requestRepository = requestRepository;
        this.requestProductRepository = requestProductRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public List<Request> findAll(LocalDate startDate, LocalDate endDate, boolean includeCanceled) {
        return includeCanceled
                ? requestRepository.findAllBetweenOrderByCreatedAtDesc(startDate, endDate)
                : requestRepository.findAllBetweenAndCanceledAtIsNullOrderByCreatedAtDesc(startDate, endDate);
    }

    public Request findByID(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }

    @Transactional
    public Request save(Request entity) {
        entity.setCustomer(customerService.selectSave(entity.getCustomer()));
        entity.setStatus(RequestStatus.CREATED);
        return requestRepository.save(entity);
    }

    @Transactional
    public Request update(Long requestID, Request payload) {
        Request request = findByID(requestID);
        updateProperties(request, payload);
        return requestRepository.save(request);
    }

    private void updateProperties(Request existing, Request payload) {
        if (payload.getDueDate() != null) existing.setDueDate(payload.getDueDate());
        existing.setNotes(payload.getNotes());
    }

    @Transactional
    public RequestProduct save(Long requestID, RequestProduct entity) {
        Request request = findByID(requestID);
        prepareRequestProduct(entity, request);
        return requestProductRepository.save(entity);
    }

    private void prepareRequestProduct(RequestProduct rp, Request request) {
        Product product = productService.findByID(rp.getRequestProductID().getProduct().getId());
        rp.setRequestProductID(new RequestProductID(request, product));
//        rp.setCalculatedProductionCost(product.calculateProductionCost());
    }

    public RequestProduct update(Long requestID, UUID productID, RequestProduct entity) {
        RequestProductID requestProductID = mountRequestProductID(requestID, productID);
        RequestProduct requestProduct = findById(requestProductID);
        copyForUpdate(requestProduct, entity);
        return requestProductRepository.save(requestProduct);
    }

    private void copyForUpdate(RequestProduct target, RequestProduct source) {
        target.setUnitaryValue(source.getUnitaryValue());
        target.setAmount(source.getAmount());
        target.setNotes(source.getNotes());
    }

    private RequestProduct findById(RequestProductID requestProductID) {
        return requestProductRepository.findById(requestProductID)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }

    private RequestProductID mountRequestProductID(Long requestID, UUID productID) {
        try {
            CompletableFuture<Request> asyncFindRequest = CompletableFuture.supplyAsync(() -> this.findByID(requestID));
            CompletableFuture<Product> asyncFindProduct = CompletableFuture.supplyAsync(() -> productService.findByID(productID));
            CompletableFuture.allOf(asyncFindRequest, asyncFindProduct);
            return new RequestProductID(asyncFindRequest.get(), asyncFindProduct.get());
        } catch (InterruptedException | ExecutionException ie) {
            throw new ResponseStatusException(INTERNAL_SERVER_ERROR, ie.getMessage());
        }
    }

    public RequestProduct findByRequestAndProductID(Long requestID, UUID productID) {
        return requestProductRepository.findByRequestProductIDRequestIdAndRequestProductIDProductId(requestID, productID)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid combination of request and product IDs"));
    }

    public void deleteProduct(Long requestID, UUID productID) {
        RequestProduct requestProduct = findByRequestAndProductID(requestID, productID);
        requestProductRepository.delete(requestProduct);
    }
}
