package com.bit.backend.services;

import com.bit.backend.dtos.EnrollmentDto;

import java.util.List;

public interface EnrollmentServiceI {

    EnrollmentDto addEnrollment(EnrollmentDto enrollmentDto);

    List<EnrollmentDto> getAllEnrollments();

    EnrollmentDto getEnrollmentById(long id);

    EnrollmentDto updateEnrollment(long id, EnrollmentDto enrollmentDto);

    EnrollmentDto deleteEnrollment(long id);
}