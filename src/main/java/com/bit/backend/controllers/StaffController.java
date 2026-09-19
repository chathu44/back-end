package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.StaffDto;
import com.bit.backend.services.StaffServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class StaffController {

    private final StaffServiceI staffServiceI;

    public StaffController(StaffServiceI staffServiceI) {
        this.staffServiceI = staffServiceI;
    }

    @GetMapping("/staff")
    public ResponseEntity<ApiListResponse<StaffDto>> getAllStaff() {
        return ResponseEntity.ok(
                ApiListResponse.of(staffServiceI.getAllStaff())
        );
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<ApiListResponse<StaffDto>> getStaffById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(staffServiceI.getStaffById(id))
        );
    }

    @PostMapping("/staff")
    public ResponseEntity<ApiListResponse<StaffDto>> addStaff(
            @RequestBody StaffDto staffDto) {
        StaffDto created = staffServiceI.addStaff(staffDto);

        return ResponseEntity
                .created(URI.create("/api/v1/staff/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/staff/{id}")
    public ResponseEntity<ApiListResponse<StaffDto>> updateStaff(
            @PathVariable long id,
            @RequestBody StaffDto staffDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        staffServiceI.updateStaff(id, staffDto)
                )
        );
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<ApiListResponse<StaffDto>> deleteStaff(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(staffServiceI.deleteStaff(id))
        );
    }
}