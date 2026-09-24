package com.bit.backend.services.impl;

import com.bit.backend.dtos.FeeInvoiceDto;
import com.bit.backend.entities.*;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.FeeInvoiceMapper;
import com.bit.backend.repositories.*;
import com.bit.backend.services.FeeInvoiceServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeeInvoiceServiceImpl implements FeeInvoiceServiceI {

    private final FeeInvoiceRepository feeInvoiceRepository;
    private final ChildRepository childRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final ParentRepository parentRepository;
    private final StatusRepository statusRepository;
    private final FeeInvoiceMapper feeInvoiceMapper;

    public FeeInvoiceServiceImpl(FeeInvoiceRepository feeInvoiceRepository,
                                 ChildRepository childRepository,
                                 EnrollmentRepository enrollmentRepository,
                                 ParentRepository parentRepository,
                                 StatusRepository statusRepository,
                                 FeeInvoiceMapper feeInvoiceMapper) {
        this.feeInvoiceRepository = feeInvoiceRepository;
        this.childRepository = childRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.parentRepository = parentRepository;
        this.statusRepository = statusRepository;
        this.feeInvoiceMapper = feeInvoiceMapper;
    }

    @Override
    @Transactional
    public FeeInvoiceDto addFeeInvoice(FeeInvoiceDto feeInvoiceDto) {
        FeeInvoiceEntity entity = feeInvoiceMapper.toFeeInvoiceEntity(feeInvoiceDto);
        entity.setId(null);
        entity.setChild(resolveChild(feeInvoiceDto));
        entity.setEnrollment(resolveEnrollment(feeInvoiceDto));
        entity.setParent(resolveParent(feeInvoiceDto));
        entity.setStatus(resolveStatus(feeInvoiceDto));

        FeeInvoiceEntity saved = feeInvoiceRepository.save(entity);

        if (saved.getInvoiceCode() == null || saved.getInvoiceCode().isBlank()) {
            saved.setInvoiceCode("INV-" + saved.getId());
            saved = feeInvoiceRepository.save(saved);
        }

        return feeInvoiceMapper.toFeeInvoiceDto(saved);
    }

    @Override
    public List<FeeInvoiceDto> getAllFeeInvoices() {
        return feeInvoiceMapper.toFeeInvoiceDtoList(feeInvoiceRepository.findAll());
    }

    @Override
    public FeeInvoiceDto getFeeInvoiceById(long id) {
        FeeInvoiceEntity entity = feeInvoiceRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Fee invoice not found", HttpStatus.NOT_FOUND
                ));

        return feeInvoiceMapper.toFeeInvoiceDto(entity);
    }

    @Override
    @Transactional
    public FeeInvoiceDto updateFeeInvoice(long id, FeeInvoiceDto feeInvoiceDto) {
        FeeInvoiceEntity existing = feeInvoiceRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Fee invoice not found", HttpStatus.NOT_FOUND
                ));

        existing.setBillingMonth(feeInvoiceDto.getBillingMonth());
        existing.setAmount(feeInvoiceDto.getAmount());
        existing.setDueDate(feeInvoiceDto.getDueDate());
        existing.setChild(resolveChild(feeInvoiceDto));
        existing.setEnrollment(resolveEnrollment(feeInvoiceDto));
        existing.setParent(resolveParent(feeInvoiceDto));
        existing.setStatus(resolveStatus(feeInvoiceDto));

        if (feeInvoiceDto.getInvoiceCode() != null
                && !feeInvoiceDto.getInvoiceCode().isBlank()) {
            existing.setInvoiceCode(feeInvoiceDto.getInvoiceCode());
        }

        return feeInvoiceMapper.toFeeInvoiceDto(
                feeInvoiceRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public FeeInvoiceDto deleteFeeInvoice(long id) {
        FeeInvoiceEntity existing = feeInvoiceRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Fee invoice not found", HttpStatus.NOT_FOUND
                ));

        FeeInvoiceDto dto = feeInvoiceMapper.toFeeInvoiceDto(existing);
        feeInvoiceRepository.delete(existing);

        return dto;
    }

    private ChildEntity resolveChild(FeeInvoiceDto dto) {
        if (dto.getChild() == null || dto.getChild().getId() == null) {
            throw new AppException("Child is required", HttpStatus.BAD_REQUEST);
        }

        return childRepository.findById(dto.getChild().getId())
                .orElseThrow(() -> new AppException(
                        "Child not found", HttpStatus.BAD_REQUEST
                ));
    }

    private EnrollmentEntity resolveEnrollment(FeeInvoiceDto dto) {
        if (dto.getEnrollment() == null || dto.getEnrollment().getId() == null) {
            throw new AppException("Enrollment is required", HttpStatus.BAD_REQUEST);
        }

        return enrollmentRepository.findById(dto.getEnrollment().getId())
                .orElseThrow(() -> new AppException(
                        "Enrollment not found", HttpStatus.BAD_REQUEST
                ));
    }

    private ParentEntity resolveParent(FeeInvoiceDto dto) {
        if (dto.getParent() == null || dto.getParent().getId() == null) {
            throw new AppException("Parent is required", HttpStatus.BAD_REQUEST);
        }

        return parentRepository.findById(dto.getParent().getId())
                .orElseThrow(() -> new AppException(
                        "Parent not found", HttpStatus.BAD_REQUEST
                ));
    }

    private StatusEntity resolveStatus(FeeInvoiceDto dto) {
        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }

        return statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found", HttpStatus.BAD_REQUEST
                ));
    }
}