package org.gdas.bigreportsapi.controller;

import org.gdas.bigreportsapi.model.enummeration.Measure;
import org.gdas.bigreportsapi.model.json.MeasureJSON;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/measures")
public class MeasureController {

    @GetMapping
    public ResponseEntity<List<MeasureJSON>> get() {
        List<MeasureJSON> result = Arrays.stream(Measure.values())
                .map(MeasureJSON::from)
                    .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}
