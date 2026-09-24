package com.bit.backend.repositories;

import com.bit.backend.entities.IncidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<IncidentEntity, Long> {
}