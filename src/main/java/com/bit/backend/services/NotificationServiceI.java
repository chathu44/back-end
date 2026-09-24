package com.bit.backend.services;

import com.bit.backend.dtos.NotificationDto;

import java.util.List;

public interface NotificationServiceI {

    NotificationDto addNotification(NotificationDto notificationDto);
    List<NotificationDto> getAllNotifications();
    NotificationDto getNotificationById(long id);
    NotificationDto updateNotification(long id, NotificationDto notificationDto);
    NotificationDto deleteNotification(long id);
}