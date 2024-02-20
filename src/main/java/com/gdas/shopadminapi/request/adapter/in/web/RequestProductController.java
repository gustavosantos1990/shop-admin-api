package com.gdas.shopadminapi.request.adapter.in.web;


import com.gdas.shopadminapi.request.application.ports.in.CreateRequestProductUseCase;
import com.gdas.shopadminapi.request.application.ports.in.DeleteRequestProductUseCase;
import com.gdas.shopadminapi.request.application.ports.in.UpdateRequestProductUseCase;
import com.gdas.shopadminapi.request.domain.RequestProduct;
import com.gdas.shopadminapi.request.domain.RequestProductId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

import static java.lang.String.format;

@RestController
@RequestMapping("/v1/requests/{requestId}/products")
public class RequestProductController {

    private final CreateRequestProductUseCase createRequestProductUseCase;
    private final UpdateRequestProductUseCase updateRequestProductUseCase;
    private final DeleteRequestProductUseCase deleteRequestProductUseCase;

    public RequestProductController(CreateRequestProductUseCase createRequestProductUseCase, UpdateRequestProductUseCase updateRequestProductUseCase, DeleteRequestProductUseCase deleteRequestProductUseCase) {
        this.createRequestProductUseCase = createRequestProductUseCase;
        this.updateRequestProductUseCase = updateRequestProductUseCase;
        this.deleteRequestProductUseCase = deleteRequestProductUseCase;
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

    @PutMapping("/{productId}")
    private ResponseEntity<RequestProduct> update(
            @PathVariable Long requestId,
            @PathVariable UUID productId,
            @Validated(UpdateRequestProductUseCase.class) @RequestBody RequestProduct requestProduct) {
        requestProduct.setRequestProductId(new RequestProductId(requestId, productId));
        RequestProduct updated = updateRequestProductUseCase.apply(requestProduct);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{productId}")
    private ResponseEntity<RequestProduct> delete(
            @PathVariable Long requestId,
            @PathVariable UUID productId) {
        deleteRequestProductUseCase.accept(new RequestProductId(requestId, productId));
        return ResponseEntity.noContent().build();
    }

}
