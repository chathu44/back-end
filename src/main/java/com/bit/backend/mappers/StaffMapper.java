package com.bit.backend.mappers;

import com.bit.backend.dtos.ClassroomDto;
import com.bit.backend.dtos.StaffDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.UserDto;
import com.bit.backend.entities.ClassroomEntity;
import com.bit.backend.entities.StaffEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface StaffMapper {

    UserDto toUserDto(UserEntity entity);

    ClassroomDto toClassroomDto(ClassroomEntity entity);

    StatusDto toStatusDto(StatusEntity entity);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "classroom", source = "classroom")
    @Mapping(target = "status", source = "status")
    StaffDto toStaffDto(StaffEntity entity);

    List<StaffDto> toStaffDtoList(List<StaffEntity> entities);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "classroom", ignore = true)
    @Mapping(target = "status", ignore = true)
    StaffEntity toStaffEntity(StaffDto dto);
}