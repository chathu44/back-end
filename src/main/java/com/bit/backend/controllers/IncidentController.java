package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.IncidentDto;
import com.bit.backend.services.IncidentServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class IncidentController {

    private final IncidentServiceI incidentServiceI;

    public IncidentController(IncidentServiceI incidentServiceI) {
        this.incidentServiceI = incidentServiceI;
    }

    @GetMapping("/incident")
    public ResponseEntity<ApiListResponse<IncidentDto>> getAllIncidents() {
        return ResponseEntity.ok(
                ApiListResponse.of(incidentServiceI.getAllIncidents())
        );
    }

    @GetMapping("/incident/{id}")
    public ResponseEntity<ApiListResponse<IncidentDto>> getIncidentById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(incidentServiceI.getIncidentById(id))
        );
    }

    @PostMapping("/incident")
    public ResponseEntity<ApiListResponse<IncidentDto>> addIncident(
            @RequestBody IncidentDto incidentDto) {
        IncidentDto created = incidentServiceI.addIncident(incidentDto);

        return ResponseEntity
                .created(URI.create("/api/v1/incident/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/incident/{id}")
    public ResponseEntity<ApiListResponse<IncidentDto>> updateIncident(
            @PathVariable long id,
            @RequestBody IncidentDto incidentDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        incidentServiceI.updateIncident(id, incidentDto)
                )
        );
    }

    @DeleteMapping("/incident/{id}")
    public ResponseEntity<ApiListResponse<IncidentDto>> deleteIncident(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(incidentServiceI.deleteIncident(id))
        );
    }
}