package com.bit.backend.services.impl;

import com.bit.backend.dtos.EnrollmentDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.EnrollmentMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.EnrollmentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentServiceI {

    private final EnrollmentRepository enrollmentRepository;
    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final ProgramRepository programRepository;
    private final ClassroomRepository classroomRepository;
    private final StatusRepository statusRepository;
    private final EnrollmentMapper enrollmentMapper;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            ChildRepository childRepository,
            ParentRepository parentRepository,
            ProgramRepository programRepository,
            ClassroomRepository classroomRepository,
            StatusRepository statusRepository,
            EnrollmentMapper enrollmentMapper) {
        this.enrollmentRepository = enrollmentRepository;
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
        this.programRepository = programRepository;
        this.classroomRepository = classroomRepository;
        this.statusRepository = statusRepository;
        this.enrollmentMapper = enrollmentMapper;
    }

    @Override
    @Transactional
    public EnrollmentDto addEnrollment(EnrollmentDto enrollmentDto) {
        ChildEntity child = resolveChild(enrollmentDto);
        ParentEntity parent = resolveParent(enrollmentDto);
        ProgramEntity program = resolveProgram(enrollmentDto);
        ClassroomEntity classroom = resolveClassroom(enrollmentDto);
        StatusEntity status = resolveStatus(enrollmentDto);

        EnrollmentEntity entity =
                enrollmentMapper.toEnrollmentEntity(enrollmentDto);

        entity.setId(null);
        entity.setChild(child);
        entity.setParent(parent);
        entity.setProgram(program);
        entity.setClassroom(classroom);
        entity.setStatus(status);

        EnrollmentEntity saved = enrollmentRepository.save(entity);

        if (saved.getEnrollmentCode() == null
                || saved.getEnrollmentCode().isBlank()) {
            saved.setEnrollmentCode("ENR-" + saved.getId());
            saved = enrollmentRepository.save(saved);
        }

        return enrollmentMapper.toEnrollmentDto(saved);
    }

    @Override
    public List<EnrollmentDto> getAllEnrollments() {
        return enrollmentMapper.toEnrollmentDtoList(
                enrollmentRepository.findAll()
        );
    }

    @Override
    public EnrollmentDto getEnrollmentById(long id) {
        EnrollmentEntity entity = enrollmentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Enrollment not found",
                        HttpStatus.NOT_FOUND
                ));

        return enrollmentMapper.toEnrollmentDto(entity);
    }

    @Override
    @Transactional
    public EnrollmentDto updateEnrollment(
            long id, EnrollmentDto enrollmentDto) {
        EnrollmentEntity existing = enrollmentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Enrollment not found",
                        HttpStatus.NOT_FOUND
                ));

        ChildEntity child = resolveChild(enrollmentDto);
        ParentEntity parent = resolveParent(enrollmentDto);
        ProgramEntity program = resolveProgram(enrollmentDto);
        ClassroomEntity classroom = resolveClassroom(enrollmentDto);
        StatusEntity status = resolveStatus(enrollmentDto);

        existing.setEnrollDate(enrollmentDto.getEnrollDate());
        existing.setStartDate(enrollmentDto.getStartDate());
        existing.setEndDate(enrollmentDto.getEndDate());
        existing.setChild(child);
        existing.setParent(parent);
        existing.setProgram(program);
        existing.setClassroom(classroom);
        existing.setStatus(status);

        if (enrollmentDto.getEnrollmentCode() != null
                && !enrollmentDto.getEnrollmentCode().isBlank()) {
            existing.setEnrollmentCode(enrollmentDto.getEnrollmentCode());
        }

        return enrollmentMapper.toEnrollmentDto(
                enrollmentRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public EnrollmentDto deleteEnrollment(long id) {
        EnrollmentEntity existing = enrollmentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Enrollment not found",
                        HttpStatus.NOT_FOUND
                ));

        EnrollmentDto dto = enrollmentMapper.toEnrollmentDto(existing);
        enrollmentRepository.delete(existing);

        return dto;
    }

    private ChildEntity resolveChild(EnrollmentDto dto) {
        if (dto.getChild() == null || dto.getChild().getId() == null) {
            throw new AppException(
                    "Child is required", HttpStatus.BAD_REQUEST
            );
        }

        return childRepository.findById(dto.getChild().getId())
                .orElseThrow(() -> new AppException(
                        "Child not found", HttpStatus.BAD_REQUEST
                ));
    }

    private ParentEntity resolveParent(EnrollmentDto dto) {
        if (dto.getParent() == null || dto.getParent().getId() == null) {
            throw new AppException(
                    "Parent is required", HttpStatus.BAD_REQUEST
            );
        }

        return parentRepository.findById(dto.getParent().getId())
                .orElseThrow(() -> new AppException(
                        "Parent not found", HttpStatus.BAD_REQUEST
                ));
    }

    private ProgramEntity resolveProgram(EnrollmentDto dto) {
        if (dto.getProgram() == null || dto.getProgram().getId() == null) {
            throw new AppException(
                    "Program is required", HttpStatus.BAD_REQUEST
            );
        }

        return programRepository.findById(dto.getProgram().getId())
                .orElseThrow(() -> new AppException(
                        "Program not found", HttpStatus.BAD_REQUEST
                ));
    }

    private ClassroomEntity resolveClassroom(EnrollmentDto dto) {
        if (dto.getClassroom() == null
                || dto.getClassroom().getId() == null) {
            throw new AppException(
                    "Classroom is required", HttpStatus.BAD_REQUEST
            );
        }

        return classroomRepository.findById(dto.getClassroom().getId())
                .orElseThrow(() -> new AppException(
                        "Classroom not found", HttpStatus.BAD_REQUEST
                ));
    }

    private StatusEntity resolveStatus(EnrollmentDto dto) {
        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException(
                    "Status is required", HttpStatus.BAD_REQUEST
            );
        }

        return statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found", HttpStatus.BAD_REQUEST
                ));
    }
}