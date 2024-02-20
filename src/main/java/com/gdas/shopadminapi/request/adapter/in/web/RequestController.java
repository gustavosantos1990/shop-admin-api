package com.gdas.shopadminapi.request.adapter.in.web;


import com.gdas.shopadminapi.request.application.ports.in.CreateRequestUseCase;
import com.gdas.shopadminapi.request.application.ports.in.FindAllRequestsUseCase;
import com.gdas.shopadminapi.request.domain.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/requests")
public class RequestController {

    private final FindAllRequestsUseCase findAllRequestsUseCase;
    private final CreateRequestUseCase createRequestUseCase;

    public RequestController(FindAllRequestsUseCase findAllRequestsUseCase, CreateRequestUseCase createRequestUseCase) {
        this.findAllRequestsUseCase = findAllRequestsUseCase;
        this.createRequestUseCase = createRequestUseCase;
    }

    @GetMapping
    private ResponseEntity<List<Request>> findAllRequests() {
        List<Request> components = findAllRequestsUseCase.get();
        return ResponseEntity.ok(components);
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

}
