package com.bit.backend.services.impl;

import com.bit.backend.dtos.ClassroomDto;
import com.bit.backend.entities.ClassroomEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ClassroomMapper;
import com.bit.backend.repositories.ClassroomRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.ClassroomServiceI;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomServiceI {

    private final ClassroomRepository classroomRepository;
    private final StatusRepository statusRepository;
    private final ClassroomMapper classroomMapper;

    public ClassroomServiceImpl(
            ClassroomRepository classroomRepository,
            StatusRepository statusRepository,
            ClassroomMapper classroomMapper) {
        this.classroomRepository = classroomRepository;
        this.statusRepository = statusRepository;
        this.classroomMapper = classroomMapper;
    }

    @Override
    @Transactional
    public ClassroomDto addClassroom(ClassroomDto classroomDto) {
        StatusEntity status = resolveStatus(classroomDto);

        ClassroomEntity entity =
                classroomMapper.toClassroomEntity(classroomDto);

        entity.setId(null);
        entity.setStatus(status);

        ClassroomEntity saved = classroomRepository.save(entity);

        if (saved.getRoomCode() == null || saved.getRoomCode().isBlank()) {
            saved.setRoomCode("RM-" + saved.getId());
            saved = classroomRepository.save(saved);
        }

        return classroomMapper.toClassroomDto(saved);
    }

    @Override
    public List<ClassroomDto> getAllClassrooms() {
        return classroomMapper.toClassroomDtoList(
                classroomRepository.findAll()
        );
    }

    @Override
    public ClassroomDto getClassroomById(long id) {
        ClassroomEntity entity = classroomRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Classroom not found",
                        HttpStatus.NOT_FOUND
                ));

        return classroomMapper.toClassroomDto(entity);
    }

    @Override
    @Transactional
    public ClassroomDto updateClassroom(
            long id, ClassroomDto classroomDto) {
        ClassroomEntity existing = classroomRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Classroom not found",
                        HttpStatus.NOT_FOUND
                ));

        StatusEntity status = resolveStatus(classroomDto);

        existing.setRoomName(classroomDto.getRoomName());
        existing.setAgeGroup(classroomDto.getAgeGroup());
        existing.setCapacity(classroomDto.getCapacity());
        existing.setFloor(classroomDto.getFloor());
        existing.setStatus(status);

        if (classroomDto.getRoomCode() != null
                && !classroomDto.getRoomCode().isBlank()) {
            existing.setRoomCode(classroomDto.getRoomCode());
        }

        return classroomMapper.toClassroomDto(
                classroomRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public ClassroomDto deleteClassroom(long id) {
        ClassroomEntity existing = classroomRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Classroom not found",
                        HttpStatus.NOT_FOUND
                ));

        ClassroomDto dto = classroomMapper.toClassroomDto(existing);

        classroomRepository.delete(existing);

        return dto;
    }

    private StatusEntity resolveStatus(ClassroomDto classroomDto) {
        if (classroomDto.getStatus() == null
                || classroomDto.getStatus().getId() == null) {
            throw new AppException(
                    "Status is required",
                    HttpStatus.BAD_REQUEST
            );
        }

        return statusRepository.findById(classroomDto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found",
                        HttpStatus.BAD_REQUEST
                ));
    }

}
