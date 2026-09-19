package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.ClassroomDto;
import com.bit.backend.services.ClassroomServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ClassroomController {

    private final ClassroomServiceI classroomServiceI;

    public ClassroomController(ClassroomServiceI classroomServiceI) {
        this.classroomServiceI = classroomServiceI;
    }

    @GetMapping("/classroom")
    public ResponseEntity<ApiListResponse<ClassroomDto>> getAllClassrooms() {
        return ResponseEntity.ok(
                ApiListResponse.of(classroomServiceI.getAllClassrooms())
        );
    }

    @GetMapping("/classroom/{id}")
    public ResponseEntity<ApiListResponse<ClassroomDto>> getClassroomById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        classroomServiceI.getClassroomById(id)
                )
        );
    }

    @PostMapping("/classroom")
    public ResponseEntity<ApiListResponse<ClassroomDto>> addClassroom(
            @RequestBody ClassroomDto classroomDto) {
        ClassroomDto created = classroomServiceI.addClassroom(classroomDto);

        return ResponseEntity
                .created(URI.create("/api/v1/classroom/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/classroom/{id}")
    public ResponseEntity<ApiListResponse<ClassroomDto>> updateClassroom(
            @PathVariable long id,
            @RequestBody ClassroomDto classroomDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        classroomServiceI.updateClassroom(id, classroomDto)
                )
        );
    }

    @DeleteMapping("/classroom/{id}")
    public ResponseEntity<ApiListResponse<ClassroomDto>> deleteClassroom(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        classroomServiceI.deleteClassroom(id)
                )
        );
    }
}
