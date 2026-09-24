package com.bit.backend.controllers;

import com.bit.backend.dtos.AdminDto;
import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.services.AdminServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class AdminController {

    private final AdminServiceI adminServiceI;

    public AdminController(AdminServiceI adminServiceI) {
        this.adminServiceI = adminServiceI;
    }

    @GetMapping("/admin")
    public ResponseEntity<ApiListResponse<AdminDto>> getAllAdmins() {
        return ResponseEntity.ok(
                ApiListResponse.of(adminServiceI.getAllAdmins())
        );
    }

    @GetMapping("/admin/{id}")
    public ResponseEntity<ApiListResponse<AdminDto>> getAdminById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(adminServiceI.getAdminById(id))
        );
    }

    @PostMapping("/admin")
    public ResponseEntity<ApiListResponse<AdminDto>> addAdmin(
            @RequestBody AdminDto adminDto) {
        AdminDto created = adminServiceI.addAdmin(adminDto);

        return ResponseEntity
                .created(URI.create("/api/v1/admin/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ApiListResponse<AdminDto>> updateAdmin(
            @PathVariable long id,
            @RequestBody AdminDto adminDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        adminServiceI.updateAdmin(id, adminDto)
                )
        );
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<ApiListResponse<AdminDto>> deleteAdmin(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(adminServiceI.deleteAdmin(id))
        );
    }
}