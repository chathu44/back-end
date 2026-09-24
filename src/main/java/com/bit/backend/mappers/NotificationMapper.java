package com.bit.backend.mappers;

import com.bit.backend.dtos.NotificationDto;
import com.bit.backend.entities.NotificationEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface NotificationMapper {

    NotificationDto toNotificationDto(NotificationEntity entity);

    List<NotificationDto> toNotificationDtoList(List<NotificationEntity> entities);

    NotificationEntity toNotificationEntity(NotificationDto dto);
}