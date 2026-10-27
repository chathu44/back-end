package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.ChildDto;
import com.bit.backend.services.ChildPhotoStorageService;
import com.bit.backend.services.ChildServiceI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ChildController {

    private final ChildServiceI childServiceI;
    private final ChildPhotoStorageService childPhotoStorageService;

    public ChildController(
            ChildServiceI childServiceI,
            ChildPhotoStorageService childPhotoStorageService) {

        this.childServiceI = childServiceI;
        this.childPhotoStorageService = childPhotoStorageService;
    }

    // Get all children
    @GetMapping("/child")
    public ResponseEntity<ApiListResponse<ChildDto>> getAllChildren() {
        return ResponseEntity.ok(
                ApiListResponse.of(childServiceI.getAllChildren())
        );
    }

    // Get one child
    @GetMapping("/child/{id}")
    public ResponseEntity<ApiListResponse<ChildDto>> getChildById(
            @PathVariable long id) {

        return ResponseEntity.ok(
                ApiListResponse.ofOne(childServiceI.getChildById(id))
        );
    }

    // Upload a child photo to the backend folder
    @PostMapping(
            value = "/child/upload-photo",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Map<String, String>> uploadChildPhoto(
            @RequestParam("file") MultipartFile file) {

        String photoUrl = childPhotoStorageService.save(file);

        return ResponseEntity.ok(
                Map.of("photoUrl", photoUrl)
        );
    }

    // Add a child
    @PostMapping("/child")
    public ResponseEntity<ApiListResponse<ChildDto>> addChild(
            @RequestBody ChildDto childDto) {

        ChildDto created = childServiceI.addChild(childDto);

        return ResponseEntity
                .created(URI.create("/api/v1/child/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    // Update a child
    @PutMapping("/child/{id}")
    public ResponseEntity<ApiListResponse<ChildDto>> updateChild(
            @PathVariable long id,
            @RequestBody ChildDto childDto) {

        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        childServiceI.updateChild(id, childDto)
                )
        );
    }

    // Delete a child
    @DeleteMapping("/child/{id}")
    public ResponseEntity<ApiListResponse<ChildDto>> deleteChild(
            @PathVariable long id) {

        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        childServiceI.deleteChild(id)
                )
        );
    }
}