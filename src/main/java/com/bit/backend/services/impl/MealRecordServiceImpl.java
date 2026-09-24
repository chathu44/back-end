package com.bit.backend.services.impl;

import com.bit.backend.dtos.MealRecordDto;
import com.bit.backend.entities.ChildEntity;
import com.bit.backend.entities.MealPlanEntity;
import com.bit.backend.entities.MealRecordEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.MealRecordMapper;
import com.bit.backend.repositories.ChildRepository;
import com.bit.backend.repositories.MealPlanRepository;
import com.bit.backend.repositories.MealRecordRepository;
import com.bit.backend.services.MealRecordServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MealRecordServiceImpl implements MealRecordServiceI {

    private final MealRecordRepository mealRecordRepository;
    private final ChildRepository childRepository;
    private final MealPlanRepository mealPlanRepository;
    private final MealRecordMapper mealRecordMapper;

    public MealRecordServiceImpl(MealRecordRepository mealRecordRepository,
                                 ChildRepository childRepository,
                                 MealPlanRepository mealPlanRepository,
                                 MealRecordMapper mealRecordMapper) {
        this.mealRecordRepository = mealRecordRepository;
        this.childRepository = childRepository;
        this.mealPlanRepository = mealPlanRepository;
        this.mealRecordMapper = mealRecordMapper;
    }

    @Override
    @Transactional
    public MealRecordDto addMealRecord(MealRecordDto mealRecordDto) {
        MealRecordEntity entity = mealRecordMapper.toMealRecordEntity(mealRecordDto);
        entity.setId(null);
        entity.setChild(resolveChild(mealRecordDto));
        entity.setMealPlan(resolveMealPlan(mealRecordDto));

        return mealRecordMapper.toMealRecordDto(mealRecordRepository.save(entity));
    }

    @Override
    public List<MealRecordDto> getAllMealRecords() {
        return mealRecordMapper.toMealRecordDtoList(mealRecordRepository.findAll());
    }

    @Override
    public MealRecordDto getMealRecordById(long id) {
        MealRecordEntity entity = mealRecordRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Meal record not found", HttpStatus.NOT_FOUND
                ));

        return mealRecordMapper.toMealRecordDto(entity);
    }

    @Override
    @Transactional
    public MealRecordDto updateMealRecord(long id, MealRecordDto mealRecordDto) {
        MealRecordEntity existing = mealRecordRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Meal record not found", HttpStatus.NOT_FOUND
                ));

        existing.setMealDate(mealRecordDto.getMealDate());
        existing.setMealType(mealRecordDto.getMealType());
        existing.setAteMeal(mealRecordDto.getAteMeal());
        existing.setNotes(mealRecordDto.getNotes());
        existing.setChild(resolveChild(mealRecordDto));
        existing.setMealPlan(resolveMealPlan(mealRecordDto));

        return mealRecordMapper.toMealRecordDto(
                mealRecordRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public MealRecordDto deleteMealRecord(long id) {
        MealRecordEntity existing = mealRecordRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Meal record not found", HttpStatus.NOT_FOUND
                ));

        MealRecordDto dto = mealRecordMapper.toMealRecordDto(existing);
        mealRecordRepository.delete(existing);

        return dto;
    }

    private ChildEntity resolveChild(MealRecordDto mealRecordDto) {
        if (mealRecordDto.getChild() == null
                || mealRecordDto.getChild().getId() == null) {
            throw new AppException(
                    "Child is required", HttpStatus.BAD_REQUEST
            );
        }

        return childRepository.findById(mealRecordDto.getChild().getId())
                .orElseThrow(() -> new AppException(
                        "Child not found", HttpStatus.BAD_REQUEST
                ));
    }

    private MealPlanEntity resolveMealPlan(MealRecordDto mealRecordDto) {
        if (mealRecordDto.getMealPlan() == null
                || mealRecordDto.getMealPlan().getId() == null) {
            throw new AppException(
                    "Meal plan is required", HttpStatus.BAD_REQUEST
            );
        }

        return mealPlanRepository.findById(mealRecordDto.getMealPlan().getId())
                .orElseThrow(() -> new AppException(
                        "Meal plan not found", HttpStatus.BAD_REQUEST
                ));
    }
}