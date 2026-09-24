package com.bit.backend.repositories;

import com.bit.backend.entities.MealPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealPlanRepository extends JpaRepository<MealPlanEntity, Long> {
}