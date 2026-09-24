package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.services.MealPlanServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class MealPlanController {

    private final MealPlanServiceI mealPlanServiceI;

    public MealPlanController(MealPlanServiceI mealPlanServiceI) {
        this.mealPlanServiceI = mealPlanServiceI;
    }

    @GetMapping("/meal-plan")
    public ResponseEntity<ApiListResponse<MealPlanDto>> getAllMealPlans() {
        return ResponseEntity.ok(
                ApiListResponse.of(mealPlanServiceI.getAllMealPlans())
        );
    }

    @GetMapping("/meal-plan/{id}")
    public ResponseEntity<ApiListResponse<MealPlanDto>> getMealPlanById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(mealPlanServiceI.getMealPlanById(id))
        );
    }

    @PostMapping("/meal-plan")
    public ResponseEntity<ApiListResponse<MealPlanDto>> addMealPlan(
            @RequestBody MealPlanDto mealPlanDto) {
        MealPlanDto created = mealPlanServiceI.addMealPlan(mealPlanDto);

        return ResponseEntity
                .created(URI.create("/api/v1/meal-plan/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/meal-plan/{id}")
    public ResponseEntity<ApiListResponse<MealPlanDto>> updateMealPlan(
            @PathVariable long id,
            @RequestBody MealPlanDto mealPlanDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        mealPlanServiceI.updateMealPlan(id, mealPlanDto)
                )
        );
    }

    @DeleteMapping("/meal-plan/{id}")
    public ResponseEntity<ApiListResponse<MealPlanDto>> deleteMealPlan(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(mealPlanServiceI.deleteMealPlan(id))
        );
    }
}