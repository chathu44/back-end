package com.bit.backend.mappers;

import com.bit.backend.dtos.ChildDto;
import com.bit.backend.dtos.ParentDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.ChildEntity;
import com.bit.backend.entities.ParentEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ChildMapper {
    ParentDto toParentDto(ParentEntity entity);
    StatusDto toStatusDto(StatusEntity entity);

    List<ParentDto> toParentDtoList(List<ParentEntity> entities);
    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "parent", source = "parent")
    @Mapping(target = "status", source = "status")
    ChildDto toChildDto(ChildEntity entity);

    List<ChildDto> toChildDtoList(List<ChildEntity> entities);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "status", ignore = true)
    ChildEntity toChildEntity(ChildDto dto);
}
