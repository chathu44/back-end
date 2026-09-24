package com.bit.backend.services.impl;

import com.bit.backend.dtos.FeePaymentDto;
import com.bit.backend.entities.FeeInvoiceEntity;
import com.bit.backend.entities.FeePaymentEntity;
import com.bit.backend.entities.StaffEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.FeePaymentMapper;
import com.bit.backend.repositories.FeeInvoiceRepository;
import com.bit.backend.repositories.FeePaymentRepository;
import com.bit.backend.repositories.StaffRepository;
import com.bit.backend.repositories.StatusRepository;
import com.bit.backend.services.FeePaymentServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeePaymentServiceImpl implements FeePaymentServiceI {

    private final FeePaymentRepository feePaymentRepository;
    private final FeeInvoiceRepository feeInvoiceRepository;
    private final StaffRepository staffRepository;
    private final StatusRepository statusRepository;
    private final FeePaymentMapper feePaymentMapper;

    public FeePaymentServiceImpl(FeePaymentRepository feePaymentRepository,
                                 FeeInvoiceRepository feeInvoiceRepository,
                                 StaffRepository staffRepository,
                                 StatusRepository statusRepository,
                                 FeePaymentMapper feePaymentMapper) {
        this.feePaymentRepository = feePaymentRepository;
        this.feeInvoiceRepository = feeInvoiceRepository;
        this.staffRepository = staffRepository;
        this.statusRepository = statusRepository;
        this.feePaymentMapper = feePaymentMapper;
    }

    @Override
    @Transactional
    public FeePaymentDto addFeePayment(FeePaymentDto feePaymentDto) {
        FeePaymentEntity entity = feePaymentMapper.toFeePaymentEntity(feePaymentDto);
        entity.setId(null);
        entity.setInvoice(resolveInvoice(feePaymentDto));
        entity.setReceivedBy(resolveStaff(feePaymentDto));
        entity.setStatus(resolveStatus(feePaymentDto));

        return feePaymentMapper.toFeePaymentDto(feePaymentRepository.save(entity));
    }

    @Override
    public List<FeePaymentDto> getAllFeePayments() {
        return feePaymentMapper.toFeePaymentDtoList(feePaymentRepository.findAll());
    }

    @Override
    public FeePaymentDto getFeePaymentById(long id) {
        FeePaymentEntity entity = feePaymentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Fee payment not found", HttpStatus.NOT_FOUND
                ));

        return feePaymentMapper.toFeePaymentDto(entity);
    }

    @Override
    @Transactional
    public FeePaymentDto updateFeePayment(long id, FeePaymentDto feePaymentDto) {
        FeePaymentEntity existing = feePaymentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Fee payment not found", HttpStatus.NOT_FOUND
                ));

        existing.setPaymentDate(feePaymentDto.getPaymentDate());
        existing.setAmount(feePaymentDto.getAmount());
        existing.setPaymentMethod(feePaymentDto.getPaymentMethod());
        existing.setReferenceNo(feePaymentDto.getReferenceNo());
        existing.setInvoice(resolveInvoice(feePaymentDto));
        existing.setReceivedBy(resolveStaff(feePaymentDto));
        existing.setStatus(resolveStatus(feePaymentDto));

        return feePaymentMapper.toFeePaymentDto(
                feePaymentRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public FeePaymentDto deleteFeePayment(long id) {
        FeePaymentEntity existing = feePaymentRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Fee payment not found", HttpStatus.NOT_FOUND
                ));

        FeePaymentDto dto = feePaymentMapper.toFeePaymentDto(existing);
        feePaymentRepository.delete(existing);

        return dto;
    }

    private FeeInvoiceEntity resolveInvoice(FeePaymentDto dto) {
        if (dto.getInvoice() == null || dto.getInvoice().getId() == null) {
            throw new AppException("Invoice is required", HttpStatus.BAD_REQUEST);
        }

        return feeInvoiceRepository.findById(dto.getInvoice().getId())
                .orElseThrow(() -> new AppException(
                        "Fee invoice not found", HttpStatus.BAD_REQUEST
                ));
    }

    private StaffEntity resolveStaff(FeePaymentDto dto) {
        if (dto.getReceivedBy() == null || dto.getReceivedBy().getId() == null) {
            throw new AppException("Receiving staff is required", HttpStatus.BAD_REQUEST);
        }

        return staffRepository.findById(dto.getReceivedBy().getId())
                .orElseThrow(() -> new AppException(
                        "Staff not found", HttpStatus.BAD_REQUEST
                ));
    }

    private StatusEntity resolveStatus(FeePaymentDto dto) {
        if (dto.getStatus() == null || dto.getStatus().getId() == null) {
            throw new AppException("Status is required", HttpStatus.BAD_REQUEST);
        }

        return statusRepository.findById(dto.getStatus().getId())
                .orElseThrow(() -> new AppException(
                        "Status not found", HttpStatus.BAD_REQUEST
                ));
    }
}