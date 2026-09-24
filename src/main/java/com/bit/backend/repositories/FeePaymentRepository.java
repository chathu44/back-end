package com.bit.backend.repositories;

import com.bit.backend.entities.FeePaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeePaymentRepository extends JpaRepository<FeePaymentEntity, Long> {
}