package com.gdas.shopadminapi.financialevent.adapter.in.web;

import com.gdas.shopadminapi.financialevent.application.ports.in.CreateEventUseCase;
import com.gdas.shopadminapi.financialevent.application.ports.in.FindAllEventsUseCase;
import com.gdas.shopadminapi.financialevent.domain.FinancialEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.lang.String.format;

@RestController
@RequestMapping("financial-events")
public class FinancialEventController {

    private final FindAllEventsUseCase findAllEventsUseCase;
    private final CreateEventUseCase createEventUseCase;

    public FinancialEventController(
            FindAllEventsUseCase findAllEventsUseCase,
            CreateEventUseCase createEventUseCase) {
        this.findAllEventsUseCase = findAllEventsUseCase;
        this.createEventUseCase = createEventUseCase;
    }

    @GetMapping
    public ResponseEntity<List<FinancialEvent>> findAllEvents() {
        List<FinancialEvent> financialEventList = findAllEventsUseCase.get();
        return ResponseEntity.ok(financialEventList);
    }

    @PostMapping
    public ResponseEntity<FinancialEvent> createEvent(@RequestBody FinancialEvent financialEvent) {
        FinancialEvent newFinancialEvent = createEventUseCase.apply(financialEvent);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path(format("/%s", newFinancialEvent.getId()))
                .buildAndExpand(newFinancialEvent)
                .toUri();
        return ResponseEntity.created(uri).body(newFinancialEvent);
    }

}
