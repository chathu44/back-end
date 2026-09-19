package com.bit.backend.services;

import com.bit.backend.dtos.AttendanceDto;

import java.util.List;

public interface AttendanceServiceI {

    AttendanceDto addAttendance(AttendanceDto attendanceDto);

    List<AttendanceDto> getAllAttendances();

    AttendanceDto getAttendanceById(long id);

    AttendanceDto updateAttendance(long id, AttendanceDto attendanceDto);

    AttendanceDto deleteAttendance(long id);
}