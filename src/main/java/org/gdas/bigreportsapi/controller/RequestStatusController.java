package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.enummeration.RequestStatus;
import org.gdas.bigreportsapi.model.json.RequestStatusJSON;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/statuses")
public class RequestStatusController {

    @GetMapping
    public ResponseEntity<List<RequestStatusJSON>> get() {
        List<RequestStatusJSON> result = Arrays.stream(RequestStatus.values())
                .map(RequestStatusJSON::from)
                    .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}
