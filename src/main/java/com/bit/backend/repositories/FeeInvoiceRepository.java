package com.bit.backend.repositories;

import com.bit.backend.entities.FeeInvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeeInvoiceRepository extends JpaRepository<FeeInvoiceEntity, Long> {
}