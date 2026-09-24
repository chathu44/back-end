package com.bit.backend.mappers;

import com.bit.backend.dtos.ClassroomDto;
import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.ClassroomEntity;
import com.bit.backend.entities.MealPlanEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface MealPlanMapper {

    StatusDto toStatusDto(StatusEntity entity);

    ClassroomDto toClassroomDto(ClassroomEntity entity);

    @Mapping(target = "classroom", source = "classroom")
    MealPlanDto toMealPlanDto(MealPlanEntity entity);

    List<MealPlanDto> toMealPlanDtoList(List<MealPlanEntity> entities);

    @Mapping(target = "classroom", ignore = true)
    MealPlanEntity toMealPlanEntity(MealPlanDto dto);
}