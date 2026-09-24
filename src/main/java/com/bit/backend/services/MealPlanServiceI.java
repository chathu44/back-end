package com.bit.backend.services;

import com.bit.backend.dtos.MealPlanDto;

import java.util.List;

public interface MealPlanServiceI {

    MealPlanDto addMealPlan(MealPlanDto mealPlanDto);

    List<MealPlanDto> getAllMealPlans();

    MealPlanDto getMealPlanById(long id);

    MealPlanDto updateMealPlan(long id, MealPlanDto mealPlanDto);

    MealPlanDto deleteMealPlan(long id);
}