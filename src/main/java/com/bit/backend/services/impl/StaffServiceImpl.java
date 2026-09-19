package com.bit.backend.services.impl;

import com.bit.backend.dtos.StaffDto;
import com.bit.backend.entities.ClassroomEntity;
import com.bit.backend.entities.StaffEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.UserEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.StaffMapper;
import com.bit.backend.repositories.ClassroomRepository;
import com.bit.backend.repositories.StaffRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.StaffServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffServiceI {

    private final StaffRepository staffRepository;
    private final UserRepository userRepository;
    private final ClassroomRepository classroomRepository;
    private final StatusRepository statusRepository;
    private final StaffMapper staffMapper;

    public StaffServiceImpl(
            StaffRepository staffRepository,
            UserRepository userRepository,
            ClassroomRepository classroomRepository,
            StatusRepository statusRepository,
            StaffMapper staffMapper) {
        this.staffRepository = staffRepository;
        this.userRepository = userRepository;
        this.classroomRepository = classroomRepository;
        this.statusRepository = statusRepository;
        this.staffMapper = staffMapper;
    }

    @Override
    @Transactional
    public StaffDto addStaff(StaffDto staffDto) {
        UserEntity user = resolveUser(staffDto);
        ClassroomEntity classroom = resolveClassroom(staffDto);
        StatusEntity status = resolveStatus(staffDto);

        StaffEntity entity = staffMapper.toStaffEntity(staffDto);

        entity.setId(null);
        entity.setUser(user);
        entity.setClassroom(classroom);
        entity.setStatus(status);

        StaffEntity saved = staffRepository.save(entity);

        if (saved.getStaffCode() == null
                || saved.getStaffCode().isBlank()) {
            saved.setStaffCode("STF-" + saved.getId());
            saved = staffRepository.save(saved);
        }

        return staffMapper.toStaffDto(saved);
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return staffMapper.toStaffDtoList(staffRepository.findAll());
    }

    @Override
    public StaffDto getStaffById(long id) {
        StaffEntity entity = staffRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Staff not found",
                        HttpStatus.NOT_FOUND
                ));

        return staffMapper.toStaffDto(entity);
    }

    @Override
    @Transactional
    public StaffDto updateStaff(long id, StaffDto staffDto) {
        StaffEntity existing = staffRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Staff not found",
                        HttpStatus.NOT_FOUND
                ));

        UserEntity user = resolveUser(staffDto);
        ClassroomEntity classroom = resolveClassroom(staffDto);
        StatusEntity status = resolveStatus(staffDto);

        existing.setFullName(staffDto.getFullName());
        existing.setRole(staffDto.getRole());
        existing.setPhone(staffDto.getPhone());
        existing.setNic(staffDto.getNic());
        existing.setHireDate(staffDto.getHireDate());
        existing.setUser(user);
        existing.setClassroom(classroom);
        existing.setStatus(status);

        if (staffDto.getStaffCode() != null
                && !staffDto.getStaffCode().isBlank()) {
            existing.setStaffCode(staffDto.getStaffCode());
        }

        return staffMapper.toStaffDto(
                staffRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public StaffDto deleteStaff(long id) {
        StaffEntity existing = staffRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Staff not found",
                        HttpStatus.NOT_FOUND
                ));

        StaffDto dto = staffMapper.toStaffDto(existing);

        staffRepository.delete(existing);

        return dto;
    }

    private UserEntity resolveUser(StaffDto dto) {
        if (dto.getUser() == null) {
            return null;
        }

        if (dto.getUser().getId() == null) {
            throw new AppException(
                    "User ID is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        return userRepository.findById(dto.getUser().getId())
                .orElseThrow(() -> new AppException(
                        "User not found",
                        HttpStatus.BAD_REQUEST
                ));
    }

    private ClassroomEntity resolveClassroom(StaffDto dto) {
        if (dto.getClassroom() == null) {
            return null;
        }

        if (dto.getClassroom().getId() == null) {
            throw new AppException(
                    "Classroom ID is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        return classroomRepository.findById(dto.getClassroom().getId())
                .orElseThrow(() -> new AppException(
                        "Classroom not found",
                        HttpStatus.BAD_REQUEST
                ));
    }

    private StatusEntity resolveStatus(StaffDto dto) {
        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException(
                    "Status is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        return statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found",
                        HttpStatus.BAD_REQUEST
                ));
    }
}