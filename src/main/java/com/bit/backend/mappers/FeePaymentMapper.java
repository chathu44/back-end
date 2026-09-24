package com.bit.backend.mappers;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface FeePaymentMapper {

    @Mapping(target = "child", ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "status", ignore = true)
    FeeInvoiceDto toFeeInvoiceDto(FeeInvoiceEntity entity);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "status", ignore = true)
    StaffDto toStaffDto(StaffEntity entity);

    StatusDto toStatusDto(StatusEntity entity);

    @Mapping(target = "invoice", source = "invoice")
    @Mapping(target = "receivedBy", source = "receivedBy")
    @Mapping(target = "status", source = "status")
    FeePaymentDto toFeePaymentDto(FeePaymentEntity entity);

    List<FeePaymentDto> toFeePaymentDtoList(List<FeePaymentEntity> entities);

    @Mapping(target = "invoice", ignore = true)
    @Mapping(target = "receivedBy", ignore = true)
    @Mapping(target = "status", ignore = true)
    FeePaymentEntity toFeePaymentEntity(FeePaymentDto dto);
}