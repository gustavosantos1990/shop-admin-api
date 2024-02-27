package com.gdas.shopadminapi.request.adapter.in.web;


import com.gdas.shopadminapi.request.application.ports.in.*;
import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/requests")
public class RequestController {

    private final FindRequestsUseCase findRequestsUseCase;
    private final FindRequestByIdUseCase findRequestByIdUseCase;
    private final CreateRequestUseCase createRequestUseCase;
    private final UpdateRequestUseCase updateRequestUseCase;
    private final UpdateRequestStatusUseCase updateRequestStatusUseCase;

    public RequestController(FindRequestsUseCase findRequestsUseCase, FindRequestByIdUseCase findRequestByIdUseCase, CreateRequestUseCase createRequestUseCase, UpdateRequestUseCase updateRequestUseCase, UpdateRequestStatusUseCase updateRequestStatusUseCase) {
        this.findRequestsUseCase = findRequestsUseCase;
        this.findRequestByIdUseCase = findRequestByIdUseCase;
        this.createRequestUseCase = createRequestUseCase;
        this.updateRequestUseCase = updateRequestUseCase;
        this.updateRequestStatusUseCase = updateRequestStatusUseCase;
    }

    @GetMapping
    private ResponseEntity<List<Request>> findAllRequests(
            @RequestParam(name = "start_date", required = false) LocalDate startDate,
            @RequestParam(name = "end_date", required = false) LocalDate endDate
    ) {
        List<Request> requests = findRequestsUseCase.apply(new FindRequestsUseCase.Command(startDate, endDate));
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{requestId}")
    private ResponseEntity<Request> findById(@PathVariable Long requestId) {
        Request request = findRequestByIdUseCase.apply(requestId);
        return ResponseEntity.ok(request);
    }

    @PostMapping
    private ResponseEntity<Request> create(@Validated(CreateRequestUseCase.class) @RequestBody Request component) {
        Request newRequest = createRequestUseCase.apply(component);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(format("/%s", newRequest.getId()))
                .buildAndExpand(newRequest)
                .toUri();
        return ResponseEntity.created(uri).body(newRequest);
    }

    @PutMapping("/{requestId}")
    private ResponseEntity<Request> update(
            @PathVariable Long requestId,
            @Validated(UpdateRequestUseCase.class) @RequestBody Request request) {
        request.setId(requestId);
        Request updated = updateRequestUseCase.apply(request);
        return ResponseEntity.ok(updated);
    }

    @PatchMapping("/{requestId}")
    private ResponseEntity<Request> updateStatus(
            @PathVariable Long requestId,
            @Validated(UpdateRequestStatusUseCase.class) @RequestBody Request request) {
        request.setId(requestId);
        Request updated = updateRequestStatusUseCase.apply(request);
        return ResponseEntity.ok(updated);
    }

}
