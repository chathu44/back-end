package com.bit.backend.services.impl;

import com.bit.backend.dtos.MealPlanDto;
import com.bit.backend.entities.ClassroomEntity;
import com.bit.backend.entities.MealPlanEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MealPlanMapper;
import com.bit.backend.repositories.ClassroomRepository;
import com.bit.backend.repositories.MealPlanRepository;
import com.bit.backend.services.MealPlanServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MealPlanServiceImpl implements MealPlanServiceI {

    private final MealPlanRepository mealPlanRepository;
    private final ClassroomRepository classroomRepository;
    private final MealPlanMapper mealPlanMapper;

    public MealPlanServiceImpl(MealPlanRepository mealPlanRepository,
                               ClassroomRepository classroomRepository,
                               MealPlanMapper mealPlanMapper) {
        this.mealPlanRepository = mealPlanRepository;
        this.classroomRepository = classroomRepository;
        this.mealPlanMapper = mealPlanMapper;
    }

    @Override
    @Transactional
    public MealPlanDto addMealPlan(MealPlanDto mealPlanDto) {
        MealPlanEntity entity = mealPlanMapper.toMealPlanEntity(mealPlanDto);
        entity.setId(null);
        entity.setClassroom(resolveClassroom(mealPlanDto));

        return mealPlanMapper.toMealPlanDto(mealPlanRepository.save(entity));
    }

    @Override
    public List<MealPlanDto> getAllMealPlans() {
        return mealPlanMapper.toMealPlanDtoList(mealPlanRepository.findAll());
    }

    @Override
    public MealPlanDto getMealPlanById(long id) {
        MealPlanEntity entity = mealPlanRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Meal plan not found", HttpStatus.NOT_FOUND
                ));

        return mealPlanMapper.toMealPlanDto(entity);
    }

    @Override
    @Transactional
    public MealPlanDto updateMealPlan(long id, MealPlanDto mealPlanDto) {
        MealPlanEntity existing = mealPlanRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Meal plan not found", HttpStatus.NOT_FOUND
                ));

        existing.setMealDate(mealPlanDto.getMealDate());
        existing.setMealType(mealPlanDto.getMealType());
        existing.setMenuItems(mealPlanDto.getMenuItems());
        existing.setClassroom(resolveClassroom(mealPlanDto));

        return mealPlanMapper.toMealPlanDto(mealPlanRepository.save(existing));
    }

    @Override
    @Transactional
    public MealPlanDto deleteMealPlan(long id) {
        MealPlanEntity existing = mealPlanRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Meal plan not found", HttpStatus.NOT_FOUND
                ));

        MealPlanDto dto = mealPlanMapper.toMealPlanDto(existing);
        mealPlanRepository.delete(existing);

        return dto;
    }

    private ClassroomEntity resolveClassroom(MealPlanDto mealPlanDto) {
        if (mealPlanDto.getClassroom() == null
                || mealPlanDto.getClassroom().getId() == null) {
            return null;
        }

        return classroomRepository.findById(mealPlanDto.getClassroom().getId())
                .orElseThrow(() -> new AppException(
                        "Classroom not found", HttpStatus.BAD_REQUEST
                ));
    }
}