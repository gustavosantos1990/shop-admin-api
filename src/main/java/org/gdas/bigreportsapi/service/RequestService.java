package org.gdas.bigreportsapi.service;

import jakarta.transaction.Transactional;
import org.gdas.bigreportsapi.model.entity.Product;
import org.gdas.bigreportsapi.model.entity.Request;
import org.gdas.bigreportsapi.model.entity.RequestProduct;
import org.gdas.bigreportsapi.model.entity.RequestProductID;
import org.gdas.bigreportsapi.repository.RequestProductRepository;
import org.gdas.bigreportsapi.repository.RequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<Request> findAll(LocalDate startDate, LocalDate endDate, boolean includeDeleted) {
        return includeDeleted
                ? requestRepository.findAllBetweenOrderByCreatedAtDesc(startDate, endDate)
                : requestRepository.findAllBetweenAndDeletedAtIsNullOrderByCreatedAtDesc(startDate, endDate);
    }

    public Request findByID(Long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invalid ID"));
    }

    @Transactional
    public Request save(Request entity) {
        entity.setCustomer(customerService.selectSave(entity.getCustomer()));
        Request savedRequest = requestRepository.save(entity);
        entity.getRequestProducts().forEach(rp -> rp.setRequestProductID(new RequestProductID(savedRequest, rp.getRequestProductID().getProduct())));
        requestProductRepository.saveAll(entity.getRequestProducts());
        return findByID(savedRequest.getId());
    }

    @Transactional
    public Request update(Long requestID, Request payload) {
        Request request = findByID(requestID);
        request.setDueDate(payload.getDueDate());
        request.setNotes(payload.getNotes());
        Request updated = requestRepository.save(request);
        updateRequestProducts(updated, payload);
        return findByID(requestID);
    }

    @Transactional
    private void updateRequestProducts(Request saved, Request payload) {
        payload.getRequestProducts().forEach(rp -> rp.setRequestProductID(new RequestProductID(saved, rp.getRequestProductID().getProduct())));

        List<RequestProduct> toDelete = new ArrayList<>();
        List<RequestProduct> toSaveUpdate = new ArrayList<>();

        List<RequestProduct> requestProducts = requestProductRepository.findByRequestProductIDRequestId(saved.getId());
        List<RequestProduct> payloadProducts = payload.getRequestProducts();

        requestProducts.removeIf(savedRequestProduct -> {
            Optional<RequestProduct> optRP = payloadProducts.stream().filter(rp -> isProductEqual(savedRequestProduct, rp)).findFirst();
            if (optRP.isEmpty()) {
                toDelete.add(savedRequestProduct);
                return true;
            }
            return false;
        });

        for (RequestProduct requestProductFromPayload : payloadProducts) {
            Optional<RequestProduct> optRP = requestProducts.stream().filter(rp -> isProductEqual(requestProductFromPayload, rp)).findFirst();
            RequestProduct toSave = optRP.orElse(requestProductFromPayload);
            updateProperties(toSave, requestProductFromPayload);
            toSaveUpdate.add(toSave);
        }

        requestProductRepository.deleteAll(toDelete);
        requestProductRepository.saveAll(toSaveUpdate);
    }

    private boolean isProductEqual(RequestProduct a, RequestProduct b) {
        return a.getRequestProductID().getRequest().getId().equals(b.getRequestProductID().getRequest().getId())
                && a.getRequestProductID().getProduct().getId().equals(b.getRequestProductID().getProduct().getId());
    }

    private void updateProperties(RequestProduct target, RequestProduct source) {
        target.setUnitaryValue(source.getUnitaryValue());
        target.setAmount(source.getAmount());
    }

    @Transactional
    public Request save(Long requestID, RequestProduct entity) {
        Request request = findByID(requestID);
        prepareRequestProduct(entity, request);
        requestProductRepository.save(entity);
        return findByID(requestID);
    }

    @Transactional
    public List<RequestProduct> saveAll(Long requestID, List<RequestProduct> entities) {
        Request request = findByID(requestID);
        entities.parallelStream().forEach(rp -> prepareRequestProduct(rp, request));
        return requestProductRepository.saveAll(entities);
    }

    private void prepareRequestProduct(RequestProduct rp, Request request) {
        Product product = productService.findByID(rp.getRequestProductID().getProduct().getId());
        rp.setRequestProductID(new RequestProductID(request, product));
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

    public Request delete(Long id) {
        Request request = findByID(id);
        request.setDeletedAt(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return requestRepository.save(request);
    }
}
