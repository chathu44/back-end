package com.bit.backend.mappers;

import com.bit.backend.dtos.AdminDto;
import com.bit.backend.dtos.StatusDto;
import com.bit.backend.dtos.UserDto;
import com.bit.backend.entities.AdminEntity;
import com.bit.backend.entities.StatusEntity;
import com.bit.backend.entities.UserEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface AdminMapper {

    UserDto toUserDto(UserEntity entity);

    StatusDto toStatusDto(StatusEntity entity);

    @Mapping(target = "user", source = "user")
    @Mapping(target = "status", source = "status")
    AdminDto toAdminDto(AdminEntity entity);

    List<AdminDto> toAdminDtoList(List<AdminEntity> entities);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "status", ignore = true)
    AdminEntity toAdminEntity(AdminDto dto);
}