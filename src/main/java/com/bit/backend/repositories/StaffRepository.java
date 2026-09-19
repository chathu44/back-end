package com.bit.backend.repositories;

import com.bit.backend.entities.StaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository
        extends JpaRepository<StaffEntity, Long> {
}