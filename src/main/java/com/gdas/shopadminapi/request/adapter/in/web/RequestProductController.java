package com.gdas.shopadminapi.request.adapter.in.web;


import com.gdas.shopadminapi.request.application.ports.in.CreateRequestProductUseCase;
import com.gdas.shopadminapi.request.domain.Request;
import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.RequestProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/requests/{requestId}/products")
public class RequestProductController {

    private final CreateRequestProductUseCase createRequestProductUseCase;

    public RequestProductController(CreateRequestProductUseCase createRequestProductUseCase) {
        this.createRequestProductUseCase = createRequestProductUseCase;
    }

    @PostMapping
    private ResponseEntity<RequestProduct> create(
            @PathVariable Long requestId,
            @Validated(CreateRequestProductUseCase.class) @RequestBody RequestProduct requestProduct) {
        requestProduct.setRequestProductId(new RequestProductId(requestId, requestProduct.getProduct().getId()));
        RequestProduct newRequest = createRequestProductUseCase.apply(requestProduct);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(format("/%s", newRequest.getProduct().getId()))
                .buildAndExpand(newRequest)
                .toUri();
        return ResponseEntity.created(uri).body(newRequest);
    }

}
