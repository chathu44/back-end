package com.bit.backend.mappers;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface IncidentMapper {

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "status", ignore = true)
    ChildDto toChildDto(ChildEntity entity);

    @Mapping(target = "status", ignore = true)
    ClassroomDto toClassroomDto(ClassroomEntity entity);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "status", ignore = true)
    StaffDto toStaffDto(StaffEntity entity);

    StatusDto toStatusDto(StatusEntity entity);

    @Mapping(target = "child", source = "child")
    @Mapping(target = "classroom", source = "classroom")
    @Mapping(target = "reportedBy", source = "reportedBy")
    @Mapping(target = "status", source = "status")
    IncidentDto toIncidentDto(IncidentEntity entity);

    List<IncidentDto> toIncidentDtoList(List<IncidentEntity> entities);

    @Mapping(target = "child", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "reportedBy", ignore = true)
    @Mapping(target = "status", ignore = true)
    IncidentEntity toIncidentEntity(IncidentDto dto);
}