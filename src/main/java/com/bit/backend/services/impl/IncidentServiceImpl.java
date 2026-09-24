package com.bit.backend.services.impl;

import com.bit.backend.dtos.IncidentDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.IncidentMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.IncidentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidentServiceImpl implements IncidentServiceI {

    private final IncidentRepository incidentRepository;
    private final ChildRepository childRepository;
    private final ClassroomRepository classroomRepository;
    private final StaffRepository staffRepository;
    private final StatusRepository statusRepository;
    private final IncidentMapper incidentMapper;

    public IncidentServiceImpl(IncidentRepository incidentRepository,
                               ChildRepository childRepository,
                               ClassroomRepository classroomRepository,
                               StaffRepository staffRepository,
                               StatusRepository statusRepository,
                               IncidentMapper incidentMapper) {
        this.incidentRepository = incidentRepository;
        this.childRepository = childRepository;
        this.classroomRepository = classroomRepository;
        this.staffRepository = staffRepository;
        this.statusRepository = statusRepository;
        this.incidentMapper = incidentMapper;
    }

    @Override
    @Transactional
    public IncidentDto addIncident(IncidentDto incidentDto) {
        IncidentEntity entity = incidentMapper.toIncidentEntity(incidentDto);
        entity.setId(null);
        entity.setChild(resolveChild(incidentDto));
        entity.setClassroom(resolveClassroom(incidentDto));
        entity.setReportedBy(resolveStaff(incidentDto));
        entity.setStatus(resolveStatus(incidentDto));

        IncidentEntity saved = incidentRepository.save(entity);

        if (saved.getIncidentCode() == null || saved.getIncidentCode().isBlank()) {
            saved.setIncidentCode("INC-" + saved.getId());
            saved = incidentRepository.save(saved);
        }

        return incidentMapper.toIncidentDto(saved);
    }

    @Override
    public List<IncidentDto> getAllIncidents() {
        return incidentMapper.toIncidentDtoList(incidentRepository.findAll());
    }

    @Override
    public IncidentDto getIncidentById(long id) {
        IncidentEntity entity = incidentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Incident not found", HttpStatus.NOT_FOUND
                ));

        return incidentMapper.toIncidentDto(entity);
    }

    @Override
    @Transactional
    public IncidentDto updateIncident(long id, IncidentDto incidentDto) {
        IncidentEntity existing = incidentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Incident not found", HttpStatus.NOT_FOUND
                ));

        existing.setIncidentDate(incidentDto.getIncidentDate());
        existing.setIncidentType(incidentDto.getIncidentType());
        existing.setDescription(incidentDto.getDescription());
        existing.setActionTaken(incidentDto.getActionTaken());
        existing.setParentNotified(incidentDto.getParentNotified());
        existing.setChild(resolveChild(incidentDto));
        existing.setClassroom(resolveClassroom(incidentDto));
        existing.setReportedBy(resolveStaff(incidentDto));
        existing.setStatus(resolveStatus(incidentDto));

        if (incidentDto.getIncidentCode() != null
                && !incidentDto.getIncidentCode().isBlank()) {
            existing.setIncidentCode(incidentDto.getIncidentCode());
        }

        return incidentMapper.toIncidentDto(incidentRepository.save(existing));
    }

    @Override
    @Transactional
    public IncidentDto deleteIncident(long id) {
        IncidentEntity existing = incidentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Incident not found", HttpStatus.NOT_FOUND
                ));

        IncidentDto dto = incidentMapper.toIncidentDto(existing);
        incidentRepository.delete(existing);

        return dto;
    }

    private ChildEntity resolveChild(IncidentDto dto) {
        if (dto.getChild() == null || dto.getChild().getId() == null) {
            throw new AppException("Child is required", HttpStatus.BAD_REQUEST);
        }

        return childRepository.findById(dto.getChild().getId())
                .orElseThrow(() -> new AppException(
                        "Child not found", HttpStatus.BAD_REQUEST
                ));
    }

    private ClassroomEntity resolveClassroom(IncidentDto dto) {
        if (dto.getClassroom() == null || dto.getClassroom().getId() == null) {
            throw new AppException("Classroom is required", HttpStatus.BAD_REQUEST);
        }

        return classroomRepository.findById(dto.getClassroom().getId())
                .orElseThrow(() -> new AppException(
                        "Classroom not found", HttpStatus.BAD_REQUEST
                ));
    }

    private StaffEntity resolveStaff(IncidentDto dto) {
        if (dto.getReportedBy() == null || dto.getReportedBy().getId() == null) {
            throw new AppException("Reporting staff is required", HttpStatus.BAD_REQUEST);
        }

        return staffRepository.findById(dto.getReportedBy().getId())
                .orElseThrow(() -> new AppException(
                        "Staff not found", HttpStatus.BAD_REQUEST
                ));
    }

    private StatusEntity resolveStatus(IncidentDto dto) {
        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }

        return statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found", HttpStatus.BAD_REQUEST
                ));
    }
}