package com.bit.backend.services;

import com.bit.backend.dtos.StaffDto;

import java.util.List;

public interface StaffServiceI {

    StaffDto addStaff(StaffDto staffDto);

    List<StaffDto> getAllStaff();

    StaffDto getStaffById(long id);

    StaffDto updateStaff(long id, StaffDto staffDto);

    StaffDto deleteStaff(long id);
}