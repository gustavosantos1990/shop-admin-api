package org.gdas.bigreportsapi.controller;

import jakarta.validation.Valid;
import org.gdas.bigreportsapi.model.ActionsGroups;
import org.gdas.bigreportsapi.model.entity.Request;
import org.gdas.bigreportsapi.model.entity.RequestProduct;
import org.gdas.bigreportsapi.model.json.RequestJSON;
import org.gdas.bigreportsapi.model.json.RequestProductJSON;
import org.gdas.bigreportsapi.service.RequestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Page<RequestJSON>> get(
            @RequestParam("start_date") LocalDate startDate,
            @RequestParam("end_date") LocalDate endDate,
            Pageable pageable) {
        Page<Request> entities = requestService.findAll(startDate, endDate, pageable);
        Page<RequestJSON> result = new PageImpl<>(
                entities.stream().map(RequestJSON::from).collect(Collectors.toList()),
                entities.getPageable(),
                entities.getTotalElements());
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
    public ResponseEntity<RequestJSON> post(@Valid @RequestBody RequestJSON payload) {
        Request newEntity = Request.from(payload);
        Request saved = requestService.save(newEntity);
        return ResponseEntity.status(CREATED).body(RequestJSON.from(saved));
    }

    @PostMapping("/{requestID}/products")
    @Validated
    public ResponseEntity<List<RequestProductJSON>> postRequestProducts(
            @PathVariable Long requestID,
            @Validated(ActionsGroups.SavingRequestProduct.class) @RequestBody List<RequestProductJSON> payload) {
        List<RequestProduct> entities = payload.stream().map(RequestProduct::from).collect(Collectors.toList());
        List<RequestProduct> newEntities = requestService.saveAll(requestID, entities);
        List<RequestProductJSON> result = newEntities.stream().map(RequestProductJSON::from).collect(Collectors.toList());
        return ResponseEntity.status(CREATED).body(result);
    }

    @PatchMapping("/{requestID}/products/{productID}")
    @Validated
    public ResponseEntity<RequestProductJSON> patchRequestProduct(
            @PathVariable Long requestID,
            @PathVariable UUID productID,
            @Validated(ActionsGroups.SavingRequestProduct.class) @RequestBody RequestProductJSON payload) {
        RequestProduct entity = RequestProduct.from(payload);
        RequestProduct updated = requestService.update(requestID, productID, entity);
        return ResponseEntity.status(CREATED).body(RequestProductJSON.from(updated));
    }

}
