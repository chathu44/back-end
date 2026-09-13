package com.bit.backend.controllers;


import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.ProgramDto;
import com.bit.backend.services.ProgramServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ProgramController {

    private final ProgramServiceI programServiceI;

    public ProgramController(ProgramServiceI programServiceI) {
        this.programServiceI = programServiceI;
    }

    @GetMapping("/program")
    public ResponseEntity<ApiListResponse<ProgramDto>> getAllPrograms() {
        return ResponseEntity.ok(
                ApiListResponse.of(programServiceI.getAllPrograms())
        );
    }

    @GetMapping("/program/{id}")
    public ResponseEntity<ApiListResponse<ProgramDto>> getProgramById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        programServiceI.getProgramById(id)
                )
        );
    }

    @PostMapping("/program")
    public ResponseEntity<ApiListResponse<ProgramDto>> addProgram(
            @RequestBody ProgramDto programDto) {
        ProgramDto created = programServiceI.addProgram(programDto);

        return ResponseEntity
                .created(URI.create("/api/v1/program/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/program/{id}")
    public ResponseEntity<ApiListResponse<ProgramDto>> updateProgram(
            @PathVariable long id,
            @RequestBody ProgramDto programDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        programServiceI.updateProgram(id, programDto)
                )
        );
    }

    @DeleteMapping("/program/{id}")
    public ResponseEntity<ApiListResponse<ProgramDto>> deleteProgram(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        programServiceI.deleteProgram(id)
                )
        );
    }
}
