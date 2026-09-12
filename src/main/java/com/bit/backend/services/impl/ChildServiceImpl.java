package com.bit.backend.services.impl;

import com.bit.backend.dtos.ChildDto;
import com.bit.backend.entities.ChildEntity;
import com.bit.backend.entities.ParentEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.ChildMapper;
import com.bit.backend.repositories.ChildRepository;
import com.bit.backend.repositories.ParentRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.ChildServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildServiceI {

    private final ChildRepository childRepository;
    private final ParentRepository parentRepository;
    private final StatusRepository statusRepository;
    private final ChildMapper childMapper;

    public ChildServiceImpl(ChildRepository childRepository,
                             ParentRepository parentRepository,
                             StatusRepository statusRepository,
                             ChildMapper childMapper) {
        this.childRepository = childRepository;
        this.parentRepository = parentRepository;
        this.statusRepository = statusRepository;
        this.childMapper = childMapper;
    }

    @Override
    @Transactional
    public ChildDto addChild(ChildDto childDto) {
        ParentEntity parent = resolveParent(childDto);
        StatusEntity status = resolveStatus(childDto);
        ChildEntity entity = childMapper.toChildEntity(childDto);
        entity.setId(null);
        entity.setParent(parent);
        entity.setStatus(status);

        ChildEntity saved = childRepository.save(entity);
        if (saved.getChildCode() == null || saved.getChildCode().isBlank()) {
            saved.setChildCode("CHD-" + saved.getId());
            saved = childRepository.save(saved);
        }
        return childMapper.toChildDto(saved);
    }

    @Override
    public List<ChildDto> getAllChildren() {
        return childMapper.toChildDtoList(childRepository.findAll());
    }

    @Override
    public ChildDto getChildById(long id) {
        ChildEntity entity = childRepository.findById(id)
                .orElseThrow(() -> new AppException("Child not found", HttpStatus.NOT_FOUND));
        return childMapper.toChildDto(entity);
    }

    @Override
    @Transactional
    public ChildDto updateChild(long id, ChildDto childDto) {
        ChildEntity existing = childRepository.findById(id)
                .orElseThrow(() -> new AppException("Child not found", HttpStatus.NOT_FOUND));

        ParentEntity parent = resolveParent(childDto);
        StatusEntity status = resolveStatus(childDto);
        existing.setFirstName(childDto.getFirstName());
        existing.setLastName(childDto.getLastName());
        existing.setDateOfBirth(childDto.getDateOfBirth());
        existing.setGender(childDto.getGender());
        existing.setBloodGroup(childDto.getBloodGroup());
        existing.setAllergies(childDto.getAllergies());
        existing.setMedicalNotes(childDto.getMedicalNotes());
        existing.setPhotoUrl(childDto.getPhotoUrl());
        existing.setParent(parent);
        existing.setStatus(status);
        if (childDto.getChildCode() != null && !childDto.getChildCode().isBlank()) {
            existing.setChildCode(childDto.getChildCode());
        }

        return childMapper.toChildDto(childRepository.save(existing));
    }

    @Override
    @Transactional
    public ChildDto deleteChild(long id) {
        ChildEntity existing = childRepository.findById(id)
                .orElseThrow(() -> new AppException("Child not found", HttpStatus.NOT_FOUND));
        ChildDto dto = childMapper.toChildDto(existing);
        childRepository.delete(existing);
        return dto;
    }

    private ParentEntity resolveParent(ChildDto childDto) {
        if (childDto.getParent() == null || childDto.getParent().getId() == null) {
            throw new AppException("Parent is required", HttpStatus.BAD_REQUEST);
        }
        return parentRepository.findById(childDto.getParent().getId())
                .orElseThrow(() -> new AppException("Parent not found", HttpStatus.BAD_REQUEST));
    }

    private StatusEntity resolveStatus(ChildDto childDto) {
        if (childDto.getStatus() == null || childDto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }
        return statusRepository.findById(childDto.getStatus().getId())
                .orElseThrow(() -> new AppException("Status not found", HttpStatus.BAD_REQUEST));
    }
}
