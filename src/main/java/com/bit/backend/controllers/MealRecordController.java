package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.MealRecordDto;
import com.bit.backend.services.MealRecordServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class MealRecordController {

    private final MealRecordServiceI mealRecordServiceI;

    public MealRecordController(MealRecordServiceI mealRecordServiceI) {
        this.mealRecordServiceI = mealRecordServiceI;
    }

    @GetMapping("/meal-record")
    public ResponseEntity<ApiListResponse<MealRecordDto>> getAllMealRecords() {
        return ResponseEntity.ok(
                ApiListResponse.of(mealRecordServiceI.getAllMealRecords())
        );
    }

    @GetMapping("/meal-record/{id}")
    public ResponseEntity<ApiListResponse<MealRecordDto>> getMealRecordById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(mealRecordServiceI.getMealRecordById(id))
        );
    }

    @PostMapping("/meal-record")
    public ResponseEntity<ApiListResponse<MealRecordDto>> addMealRecord(
            @RequestBody MealRecordDto mealRecordDto) {
        MealRecordDto created = mealRecordServiceI.addMealRecord(mealRecordDto);

        return ResponseEntity
                .created(URI.create("/api/v1/meal-record/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/meal-record/{id}")
    public ResponseEntity<ApiListResponse<MealRecordDto>> updateMealRecord(
            @PathVariable long id,
            @RequestBody MealRecordDto mealRecordDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        mealRecordServiceI.updateMealRecord(id, mealRecordDto)
                )
        );
    }

    @DeleteMapping("/meal-record/{id}")
    public ResponseEntity<ApiListResponse<MealRecordDto>> deleteMealRecord(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(mealRecordServiceI.deleteMealRecord(id))
        );
    }
}