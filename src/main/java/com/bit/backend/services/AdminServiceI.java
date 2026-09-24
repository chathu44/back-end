package com.bit.backend.services;

import com.bit.backend.dtos.AdminDto;

import java.util.List;

public interface AdminServiceI {

    AdminDto addAdmin(AdminDto adminDto);
    List<AdminDto> getAllAdmins();
    AdminDto getAdminById(long id);
    AdminDto updateAdmin(long id, AdminDto adminDto);
    AdminDto deleteAdmin(long id);
}