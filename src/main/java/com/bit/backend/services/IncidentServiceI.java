package com.bit.backend.services;

import com.bit.backend.dtos.IncidentDto;

import java.util.List;

public interface IncidentServiceI {

    IncidentDto addIncident(IncidentDto incidentDto);
    List<IncidentDto> getAllIncidents();
    IncidentDto getIncidentById(long id);
    IncidentDto updateIncident(long id, IncidentDto incidentDto);
    IncidentDto deleteIncident(long id);
}