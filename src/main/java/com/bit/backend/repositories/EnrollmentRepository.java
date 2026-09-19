package com.bit.backend.repositories;

import com.bit.backend.entities.EnrollmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository
        extends JpaRepository<EnrollmentEntity, Long> {
}