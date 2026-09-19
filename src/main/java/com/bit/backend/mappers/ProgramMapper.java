package com.bit.backend.mappers;

import com.bit.backend.dtos.ProgramDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.ProgramEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProgramMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    ProgramDto toProgramDto(ProgramEntity entity);

    List<ProgramDto> toProgramDtoList(List<ProgramEntity> entities);

    @Mapping(target = "status", ignore = true)
    ProgramEntity toProgramEntity(ProgramDto dto);
}
