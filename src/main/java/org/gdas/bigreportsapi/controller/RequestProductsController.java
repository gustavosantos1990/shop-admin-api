package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.actions.IncludingNewRequestProduct;
import org.gdas.bigreportsapi.model.actions.UpdatingRequestProduct;
import org.gdas.bigreportsapi.model.entity.Request;
import org.gdas.bigreportsapi.model.entity.RequestProduct;
import org.gdas.bigreportsapi.model.json.RequestProductJSON;
import org.gdas.bigreportsapi.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/requests/{requestID}/products")
@Validated
public class RequestProductsController {

    private final RequestService requestService;

    public RequestProductsController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    @Validated
    public ResponseEntity<List<RequestProductJSON>> get(
            @PathVariable Long requestID) {
        Request request = requestService.findByID(requestID);
        List<RequestProductJSON> result =
                request.getRequestProducts().stream()
                        .map(RequestProductJSON::from)
                        .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @PostMapping
    @Validated
    public ResponseEntity<RequestProductJSON> post(
            @PathVariable Long requestID,
            @Validated(IncludingNewRequestProduct.class) @RequestBody RequestProductJSON payload) {
        RequestProduct entity = RequestProduct.from(payload);
        RequestProduct saved = requestService.save(requestID, entity);
        return ResponseEntity.status(CREATED).body(RequestProductJSON.from(saved));
    }

    @PutMapping("/{productID}")
    @Validated
    public ResponseEntity<RequestProductJSON> put(
            @PathVariable Long requestID,
            @PathVariable UUID productID,
            @Validated(UpdatingRequestProduct.class) @RequestBody RequestProductJSON payload) {
        RequestProduct entity = RequestProduct.from(payload);
        RequestProduct updated = requestService.update(requestID, productID, entity);
        return ResponseEntity.status(CREATED).body(RequestProductJSON.from(updated));
    }

    @DeleteMapping("/{productID}")
    @Validated
    public ResponseEntity<Void> delete(
            @PathVariable Long requestID,
            @PathVariable UUID productID) {
        requestService.deleteProduct(requestID, productID);
        return ResponseEntity.noContent().build();
    }

}
