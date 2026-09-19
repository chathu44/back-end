package com.bit.backend.mappers;

import com.bit.backend.dtos.*;
import com.bit.backend.entities.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AttendanceMapper {

    ChildDto toChildDto(ChildEntity entity);

    ParentDto toParentDto(ParentEntity entity);

    ClassroomDto toClassroomDto(ClassroomEntity entity);

    StatusDto toStatusDto(StatusEntity entity);

    StaffDto toStaffDto(StaffEntity entity);

    @Mapping(target = "child", source = "child")
    @Mapping(target = "classroom", source = "classroom")
    @Mapping(target = "status", source = "status")
    @Mapping(target = "checkedInBy", source = "checkedInBy")
    @Mapping(target = "checkedOutBy", source = "checkedOutBy")
    AttendanceDto toAttendanceDto(AttendanceEntity entity);

    List<AttendanceDto> toAttendanceDtoList(
            List<AttendanceEntity> entities
    );

    @Mapping(target = "child", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "checkedInBy", ignore = true)
    @Mapping(target = "checkedOutBy", ignore = true)
    AttendanceEntity toAttendanceEntity(AttendanceDto dto);
}