package com.bit.backend.services;

import com.bit.backend.dtos.MealRecordDto;

import java.util.List;

public interface MealRecordServiceI {

    MealRecordDto addMealRecord(MealRecordDto mealRecordDto);

    List<MealRecordDto> getAllMealRecords();

    MealRecordDto getMealRecordById(long id);

    MealRecordDto updateMealRecord(long id, MealRecordDto mealRecordDto);

    MealRecordDto deleteMealRecord(long id);
}