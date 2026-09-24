package com.bit.backend.mappers;

import com.bit.backend.dtos.ChildDto;
import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.dtos.MealRecordDto;
import com.bit.backend.entities.ChildEntity;
import com.bit.backend.entities.MealPlanEntity;
import com.bit.backend.entities.MealRecordEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MealRecordMapper {

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "status", ignore = true)
    ChildDto toChildDto(ChildEntity entity);

    @Mapping(target = "classroom", ignore = true)
    MealPlanDto toMealPlanDto(MealPlanEntity entity);

    @Mapping(target = "child", source = "child")
    @Mapping(target = "mealPlan", source = "mealPlan")
    MealRecordDto toMealRecordDto(MealRecordEntity entity);

    List<MealRecordDto> toMealRecordDtoList(List<MealRecordEntity> entities);

    @Mapping(target = "child", ignore = true)
    @Mapping(target = "mealPlan", ignore = true)
    MealRecordEntity toMealRecordEntity(MealRecordDto dto);
}