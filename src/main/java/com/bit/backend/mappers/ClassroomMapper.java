package com.bit.backend.mappers;

import com.bit.backend.dtos.ClassroomDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.entities.ClassroomEntity;
import com.bit.backend.entities.StatusEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ClassroomMapper {

    StatusDto toStatusDto(StatusEntity entity);

    List<StatusDto> toStatusDtoList(List<StatusEntity> entities);

    @Mapping(target = "status", source = "status")
    ClassroomDto toClassroomDto(ClassroomEntity entity);

    List<ClassroomDto> toClassroomDtoList(List<ClassroomEntity> entities);

    @Mapping(target = "status", ignore = true)
    ClassroomEntity toClassroomEntity(ClassroomDto dto);
}
