package com.bit.backend.repositories;

import com.bit.backend.entities.MealRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRecordRepository extends JpaRepository<MealRecordEntity, Long> {
}