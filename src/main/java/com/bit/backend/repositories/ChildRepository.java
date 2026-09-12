package com.bit.backend.repositories;

import com.bit.backend.entities.ChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepository extends JpaRepository<ChildEntity, Long> {
}
