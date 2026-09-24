package com.bit.backend.mappers;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface FeeInvoiceMapper {

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "status", ignore = true)
    ChildDto toChildDto(ChildEntity entity);

    @Mapping(target = "status", ignore = true)
    ParentDto toParentDto(ParentEntity entity);

    @Mapping(target = "child", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "program", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "status", ignore = true)
    EnrollmentDto toEnrollmentDto(EnrollmentEntity entity);

    StatusDto toStatusDto(StatusEntity entity);

    @Mapping(target = "child", source = "child")
    @Mapping(target = "enrollment", source = "enrollment")
    @Mapping(target = "parent", source = "parent")
    @Mapping(target = "status", source = "status")
    FeeInvoiceDto toFeeInvoiceDto(FeeInvoiceEntity entity);

    List<FeeInvoiceDto> toFeeInvoiceDtoList(List<FeeInvoiceEntity> entities);

    @Mapping(target = "child", ignore = true)
    @Mapping(target = "enrollment", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "status", ignore = true)
    FeeInvoiceEntity toFeeInvoiceEntity(FeeInvoiceDto dto);
}