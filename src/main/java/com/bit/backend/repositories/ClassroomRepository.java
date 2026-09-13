package com.bit.backend.repositories;

import com.bit.backend.entities.ClassroomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<ClassroomEntity, Long> {
}
