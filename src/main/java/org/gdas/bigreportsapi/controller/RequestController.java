package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.actions.SavingNewRequest;
import org.gdas.bigreportsapi.model.actions.UpdatingRequest;
import org.gdas.bigreportsapi.model.entity.Request;
import org.gdas.bigreportsapi.model.enummeration.RequestStatus;
import org.gdas.bigreportsapi.model.json.RequestJSON;
import org.gdas.bigreportsapi.service.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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
            @RequestParam(value = "start_date", required = false) LocalDate startDate,
            @RequestParam(value = "end_date", required = false) LocalDate endDate) {

        if (startDate == null || endDate == null) {
            startDate = LocalDate.now().withDayOfMonth(1);
            endDate = LocalDate.now().plusMonths(1).minusDays(1);
        }

        List<Request> entities = requestService.findAll(startDate, endDate);
        List<RequestJSON> result = entities.stream().map(RequestJSON::from).collect(Collectors.toList());
        return ResponseEntity.ok(result);
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

    @PatchMapping("/{requestID}/status/{newStatus}")
    public ResponseEntity<RequestJSON> patch(
            @PathVariable Long requestID,
            @PathVariable RequestStatus newStatus) {
        Request updated = requestService.updateStatus(requestID, newStatus);
        return ResponseEntity.ok(RequestJSON.from(updated));
    }

}
