package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.actions.SavingNewRequest;
import org.gdas.bigreportsapi.model.actions.UpdatingRequest;
import org.gdas.bigreportsapi.model.entity.Request;
import org.gdas.bigreportsapi.model.entity.RequestProduct;
import org.gdas.bigreportsapi.model.json.RequestJSON;
import org.gdas.bigreportsapi.model.json.RequestProductJSON;
import org.gdas.bigreportsapi.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/requests")
@Validated
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public ResponseEntity<List<RequestJSON>> get(
            @RequestParam("start_date") LocalDate startDate,
            @RequestParam("end_date") LocalDate endDate,
            @RequestParam(value = "include_deleted", defaultValue = "false") boolean includeDeleted) {
        List<Request> entities = requestService.findAll(startDate, endDate, includeDeleted);
        List<RequestJSON> result = entities.stream().map(RequestJSON::from).collect(Collectors.toList());
        return result.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RequestJSON> getByID(@PathVariable Long id) {
        Request entity = requestService.findByID(id);
        return ResponseEntity.ok(RequestJSON.from(entity));
    }

    @PostMapping
    @Validated
    public ResponseEntity<RequestJSON> post(@Validated(SavingNewRequest.class) @RequestBody RequestJSON payload) {
        Request newEntity = Request.from(payload);
        Request saved = requestService.save(newEntity);
        return ResponseEntity.status(CREATED).body(RequestJSON.from(saved));
    }

    @PutMapping("/{requestID}")
    @Validated
    public ResponseEntity<RequestJSON> put(
            @PathVariable Long requestID,
            @Validated(UpdatingRequest.class) @RequestBody RequestJSON payload) {
        Request entity = Request.from(payload);
        Request updated = requestService.update(requestID, entity);
        return ResponseEntity.ok(RequestJSON.from(updated));
    }

    @GetMapping("/{requestID}/products")
    @Validated
    public ResponseEntity<List<RequestProductJSON>> getRequestProducts(
            @PathVariable Long requestID) {
        Request request = requestService.findByID(requestID);
        List<RequestProductJSON> result =
                request.getRequestProducts().stream()
                        .map(RequestProductJSON::from)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{requestID}/products")
    @Validated
    public ResponseEntity<RequestProductJSON> postRequestProducts(
            @PathVariable Long requestID,
            @Validated @RequestBody RequestProductJSON payload) {
        RequestProduct entity = RequestProduct.from(payload);
        RequestProduct saved = requestService.save(requestID, entity);
        return ResponseEntity.status(CREATED).body(RequestProductJSON.from(saved));
    }

    @PatchMapping("/{requestID}/products/{productID}")
    @Validated
    public ResponseEntity<RequestProductJSON> patchRequestProduct(
            @PathVariable Long requestID,
            @PathVariable UUID productID,
            @Validated @RequestBody RequestProductJSON payload) {
        RequestProduct entity = RequestProduct.from(payload);
        RequestProduct updated = requestService.update(requestID, productID, entity);
        return ResponseEntity.status(CREATED).body(RequestProductJSON.from(updated));
    }

    @DeleteMapping("/{requestID}/products/{productID}")
    @Validated
    public ResponseEntity<Void> deleteRequestProduct(
            @PathVariable Long requestID,
            @PathVariable UUID productID) {
        requestService.deleteProduct(requestID, productID);
        return ResponseEntity.noContent().build();
    }

}
