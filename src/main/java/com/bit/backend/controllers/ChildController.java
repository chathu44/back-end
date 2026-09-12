package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.ChildDto;
import com.bit.backend.services.ChildServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class ChildController {

    private final ChildServiceI childServiceI;

    public ChildController(ChildServiceI childServiceI) {
        this.childServiceI = childServiceI;
    }

    @GetMapping("/child")
    public ResponseEntity<ApiListResponse<ChildDto>> getAllChildren() {
        return ResponseEntity.ok(ApiListResponse.of(childServiceI.getAllChildren()));
    }

    @GetMapping("/child/{id}")
    public ResponseEntity<ApiListResponse<ChildDto>> getChildById(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(childServiceI.getChildById(id)));
    }

    @PostMapping("/child")
    public ResponseEntity<ApiListResponse<ChildDto>> addChild(@RequestBody ChildDto childDto) {
        ChildDto created = childServiceI.addChild(childDto);
        return ResponseEntity.created(URI.create("/api/v1/child/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/child/{id}")
    public ResponseEntity<ApiListResponse<ChildDto>> updateChild(
            @PathVariable long id,
            @RequestBody ChildDto childDto) {
        return ResponseEntity.ok(ApiListResponse.ofOne(childServiceI.updateChild(id, childDto)));
    }

    @DeleteMapping("/child/{id}")
    public ResponseEntity<ApiListResponse<ChildDto>> deleteChild(@PathVariable long id) {
        return ResponseEntity.ok(ApiListResponse.ofOne(childServiceI.deleteChild(id)));
    }
}