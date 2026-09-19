package com.bit.backend.mappers;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface EnrollmentMapper {

    ChildDto toChildDto(ChildEntity entity);

    ParentDto toParentDto(ParentEntity entity);

    ProgramDto toProgramDto(ProgramEntity entity);

    ClassroomDto toClassroomDto(ClassroomEntity entity);

    StatusDto toStatusDto(StatusEntity entity);

    @Mapping(target = "child", source = "child")
    @Mapping(target = "parent", source = "parent")
    @Mapping(target = "program", source = "program")
    @Mapping(target = "classroom", source = "classroom")
    @Mapping(target = "status", source = "status")
    EnrollmentDto toEnrollmentDto(EnrollmentEntity entity);

    List<EnrollmentDto> toEnrollmentDtoList(
            List<EnrollmentEntity> entities
    );

    @Mapping(target = "child", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "program", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "status", ignore = true)
    EnrollmentEntity toEnrollmentEntity(EnrollmentDto dto);
}