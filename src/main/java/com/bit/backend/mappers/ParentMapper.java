package com.bit.backend.mappers;

import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.ParentDto;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.ParentEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ParentMapper {
    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    ParentDto toParentDto(ParentEntity entity);

    List<ParentDto> toParentDtoList(List<ParentEntity> entities);

    @Mapping(target = "status", ignore = true)
    ParentEntity toParent(ParentDto dto);
}
