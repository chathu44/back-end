package com.bit.backend.services.impl;

import com.bit.backend.dtos.AttendanceDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AttendanceMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.AttendanceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceServiceI {

    private final AttendanceRepository attendanceRepository;
    private final ChildRepository childRepository;
    private final ClassroomRepository classroomRepository;
    private final StaffRepository staffRepository;
    private final StatusRepository statusRepository;
    private final AttendanceMapper attendanceMapper;

    public AttendanceServiceImpl(
            AttendanceRepository attendanceRepository,
            ChildRepository childRepository,
            ClassroomRepository classroomRepository,
            StaffRepository staffRepository,
            StatusRepository statusRepository,
            AttendanceMapper attendanceMapper) {
        this.attendanceRepository = attendanceRepository;
        this.childRepository = childRepository;
        this.classroomRepository = classroomRepository;
        this.staffRepository = staffRepository;
        this.statusRepository = statusRepository;
        this.attendanceMapper = attendanceMapper;
    }

    @Override
    @Transactional
    public AttendanceDto addAttendance(AttendanceDto attendanceDto) {
        ChildEntity child = resolveChild(attendanceDto);
        ClassroomEntity classroom = resolveClassroom(attendanceDto);
        StatusEntity status = resolveStatus(attendanceDto);
        StaffEntity checkedInBy = resolveCheckedInBy(attendanceDto);
        StaffEntity checkedOutBy = resolveCheckedOutBy(attendanceDto);

        AttendanceEntity entity =
                attendanceMapper.toAttendanceEntity(attendanceDto);

        entity.setId(null);
        entity.setChild(child);
        entity.setClassroom(classroom);
        entity.setStatus(status);
        entity.setCheckedInBy(checkedInBy);
        entity.setCheckedOutBy(checkedOutBy);

        AttendanceEntity saved = attendanceRepository.save(entity);

        return attendanceMapper.toAttendanceDto(saved);
    }

    @Override
    public List<AttendanceDto> getAllAttendances() {
        return attendanceMapper.toAttendanceDtoList(
                attendanceRepository.findAll()
        );
    }

    @Override
    public AttendanceDto getAttendanceById(long id) {
        AttendanceEntity entity = attendanceRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Attendance not found",
                        HttpStatus.NOT_FOUND
                ));

        return attendanceMapper.toAttendanceDto(entity);
    }

    @Override
    @Transactional
    public AttendanceDto updateAttendance(
            long id, AttendanceDto attendanceDto) {
        AttendanceEntity existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Attendance not found",
                        HttpStatus.NOT_FOUND
                ));

        ChildEntity child = resolveChild(attendanceDto);
        ClassroomEntity classroom = resolveClassroom(attendanceDto);
        StatusEntity status = resolveStatus(attendanceDto);
        StaffEntity checkedInBy = resolveCheckedInBy(attendanceDto);
        StaffEntity checkedOutBy = resolveCheckedOutBy(attendanceDto);

        existing.setAttendanceDate(attendanceDto.getAttendanceDate());
        existing.setCheckInTime(attendanceDto.getCheckInTime());
        existing.setCheckOutTime(attendanceDto.getCheckOutTime());
        existing.setPickupPerson(attendanceDto.getPickupPerson());
        existing.setNotes(attendanceDto.getNotes());
        existing.setChild(child);
        existing.setClassroom(classroom);
        existing.setStatus(status);
        existing.setCheckedInBy(checkedInBy);
        existing.setCheckedOutBy(checkedOutBy);

        return attendanceMapper.toAttendanceDto(
                attendanceRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public AttendanceDto deleteAttendance(long id) {
        AttendanceEntity existing = attendanceRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Attendance not found",
                        HttpStatus.NOT_FOUND
                ));

        AttendanceDto dto = attendanceMapper.toAttendanceDto(existing);
        attendanceRepository.delete(existing);

        return dto;
    }

    private ChildEntity resolveChild(AttendanceDto dto) {
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

    private ClassroomEntity resolveClassroom(AttendanceDto dto) {
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

    private StatusEntity resolveStatus(AttendanceDto dto) {
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

    private StaffEntity resolveCheckedInBy(AttendanceDto dto) {
        if (dto.getCheckedInBy() == null || dto.getCheckedInBy().getId() == null) {
            throw new AppException("Check-in staff ID is required", HttpStatus.BAD_REQUEST);
        }
        return staffRepository.findById(dto.getCheckedInBy().getId())
                .orElseThrow(() -> new AppException("Check-in staff not found", HttpStatus.BAD_REQUEST));
    }

    private StaffEntity resolveCheckedOutBy(AttendanceDto dto) {
        if (dto.getCheckedOutBy() == null || dto.getCheckedOutBy().getId() == null) {
            throw new AppException("Check-out staff ID is required", HttpStatus.BAD_REQUEST);
        }
        return staffRepository.findById(dto.getCheckedOutBy().getId())
                .orElseThrow(() -> new AppException("Check-out staff not found", HttpStatus.BAD_REQUEST));
    }

}