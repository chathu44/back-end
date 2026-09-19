package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.AttendanceDto;
import com.bit.backend.services.AttendanceServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class AttendanceController {

    private final AttendanceServiceI attendanceServiceI;

    public AttendanceController(AttendanceServiceI attendanceServiceI) {
        this.attendanceServiceI = attendanceServiceI;
    }

    @GetMapping("/attendance")
    public ResponseEntity<ApiListResponse<AttendanceDto>> getAllAttendances() {
        return ResponseEntity.ok(
                ApiListResponse.of(attendanceServiceI.getAllAttendances())
        );
    }

    @GetMapping("/attendance/{id}")
    public ResponseEntity<ApiListResponse<AttendanceDto>> getAttendanceById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        attendanceServiceI.getAttendanceById(id)
                )
        );
    }

    @PostMapping("/attendance")
    public ResponseEntity<ApiListResponse<AttendanceDto>> addAttendance(
            @RequestBody AttendanceDto attendanceDto) {
        AttendanceDto created =
                attendanceServiceI.addAttendance(attendanceDto);

        return ResponseEntity
                .created(URI.create("/api/v1/attendance/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/attendance/{id}")
    public ResponseEntity<ApiListResponse<AttendanceDto>> updateAttendance(
            @PathVariable long id,
            @RequestBody AttendanceDto attendanceDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        attendanceServiceI.updateAttendance(id, attendanceDto)
                )
        );
    }

    @DeleteMapping("/attendance/{id}")
    public ResponseEntity<ApiListResponse<AttendanceDto>> deleteAttendance(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        attendanceServiceI.deleteAttendance(id)
                )
        );
    }
}