package com.bit.backend.services.impl;

import com.bit.backend.dtos.AdminDto;
import com.bit.backend.entities.AdminEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.UserEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.AdminMapper;
import com.bit.backend.repositories.AdminRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.repositories.UserRepository;
import com.bit.backend.services.AdminServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminServiceI {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;
    private final StatusRepository statusRepository;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminRepository adminRepository,
                            UserRepository userRepository,
                            StatusRepository statusRepository,
                            AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.adminMapper = adminMapper;
    }

    @Override
    @Transactional
    public AdminDto addAdmin(AdminDto adminDto) {
        AdminEntity entity = adminMapper.toAdminEntity(adminDto);
        entity.setId(null);
        entity.setUser(resolveUser(adminDto));
        entity.setStatus(resolveStatus(adminDto));

        AdminEntity saved = adminRepository.save(entity);

        if (saved.getAdminCode() == null || saved.getAdminCode().isBlank()) {
            saved.setAdminCode("ADM-" + saved.getId());
            saved = adminRepository.save(saved);
        }

        return adminMapper.toAdminDto(saved);
    }

    @Override
    public List<AdminDto> getAllAdmins() {
        return adminMapper.toAdminDtoList(adminRepository.findAll());
    }

    @Override
    public AdminDto getAdminById(long id) {
        AdminEntity entity = adminRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Admin not found", HttpStatus.NOT_FOUND
                ));

        return adminMapper.toAdminDto(entity);
    }

    @Override
    @Transactional
    public AdminDto updateAdmin(long id, AdminDto adminDto) {
        AdminEntity existing = adminRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Admin not found", HttpStatus.NOT_FOUND
                ));

        existing.setPhone(adminDto.getPhone());
        existing.setUser(resolveUser(adminDto));
        existing.setStatus(resolveStatus(adminDto));

        if (adminDto.getAdminCode() != null
                && !adminDto.getAdminCode().isBlank()) {
            existing.setAdminCode(adminDto.getAdminCode());
        }

        return adminMapper.toAdminDto(adminRepository.save(existing));
    }

    @Override
    @Transactional
    public AdminDto deleteAdmin(long id) {
        AdminEntity existing = adminRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Admin not found", HttpStatus.NOT_FOUND
                ));

        AdminDto dto = adminMapper.toAdminDto(existing);
        adminRepository.delete(existing);

        return dto;
    }

    private UserEntity resolveUser(AdminDto dto) {
        if (dto.getUser() == null || dto.getUser().getId() == null) {
            throw new AppException("User is required", HttpStatus.BAD_REQUEST);
        }

        return userRepository.findById(dto.getUser().getId())
                .orElseThrow(() -> new AppException(
                        "User not found", HttpStatus.BAD_REQUEST
                ));
    }

    private StatusEntity resolveStatus(AdminDto dto) {
        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }

        return statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found", HttpStatus.BAD_REQUEST
                ));
    }
}