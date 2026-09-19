package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.EnrollmentDto;
import com.bit.backend.services.EnrollmentServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class EnrollmentController {

    private final EnrollmentServiceI enrollmentServiceI;

    public EnrollmentController(EnrollmentServiceI enrollmentServiceI) {
        this.enrollmentServiceI = enrollmentServiceI;
    }

    @GetMapping("/enrollment")
    public ResponseEntity<ApiListResponse<EnrollmentDto>> getAllEnrollments() {
        return ResponseEntity.ok(
                ApiListResponse.of(enrollmentServiceI.getAllEnrollments())
        );
    }

    @GetMapping("/enrollment/{id}")
    public ResponseEntity<ApiListResponse<EnrollmentDto>> getEnrollmentById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        enrollmentServiceI.getEnrollmentById(id)
                )
        );
    }

    @PostMapping("/enrollment")
    public ResponseEntity<ApiListResponse<EnrollmentDto>> addEnrollment(
            @RequestBody EnrollmentDto enrollmentDto) {
        EnrollmentDto created =
                enrollmentServiceI.addEnrollment(enrollmentDto);

        return ResponseEntity
                .created(URI.create("/api/v1/enrollment/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/enrollment/{id}")
    public ResponseEntity<ApiListResponse<EnrollmentDto>> updateEnrollment(
            @PathVariable long id,
            @RequestBody EnrollmentDto enrollmentDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        enrollmentServiceI.updateEnrollment(id, enrollmentDto)
                )
        );
    }

    @DeleteMapping("/enrollment/{id}")
    public ResponseEntity<ApiListResponse<EnrollmentDto>> deleteEnrollment(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        enrollmentServiceI.deleteEnrollment(id)
                )
        );
    }
}