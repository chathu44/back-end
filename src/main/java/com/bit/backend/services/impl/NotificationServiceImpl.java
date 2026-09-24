package com.bit.backend.services.impl;

import com.bit.backend.dtos.NotificationDto;
import com.bit.backend.entities.NotificationEntity;
import com.bit.backend.exceptions.AppException;
import com.bit.backend.mappers.NotificationMapper;
import com.bit.backend.repositories.NotificationRepository;
import com.bit.backend.services.NotificationServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationServiceI {

    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public NotificationServiceImpl(NotificationRepository notificationRepository,
                                   NotificationMapper notificationMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
    }

    @Override
    @Transactional
    public NotificationDto addNotification(NotificationDto notificationDto) {
        NotificationEntity entity = notificationMapper.toNotificationEntity(notificationDto);
        entity.setId(null);

        if (entity.getSentDate() == null) {
            entity.setSentDate(LocalDateTime.now());
        }

        if (entity.getReadStatus() == null) {
            entity.setReadStatus(false);
        }

        return notificationMapper.toNotificationDto(
                notificationRepository.save(entity)
        );
    }

    @Override
    public List<NotificationDto> getAllNotifications() {
        return notificationMapper.toNotificationDtoList(
                notificationRepository.findAll()
        );
    }

    @Override
    public NotificationDto getNotificationById(long id) {
        NotificationEntity entity = notificationRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Notification not found", HttpStatus.NOT_FOUND
                ));

        return notificationMapper.toNotificationDto(entity);
    }

    @Override
    @Transactional
    public NotificationDto updateNotification(
            long id,
            NotificationDto notificationDto) {
        NotificationEntity existing = notificationRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Notification not found", HttpStatus.NOT_FOUND
                ));

        existing.setRecipientId(notificationDto.getRecipientId());
        existing.setRecipientType(notificationDto.getRecipientType());
        existing.setMessage(notificationDto.getMessage());
        existing.setType(notificationDto.getType());
        existing.setSentDate(notificationDto.getSentDate());
        existing.setReadStatus(notificationDto.getReadStatus());

        return notificationMapper.toNotificationDto(
                notificationRepository.save(existing)
        );
    }

    @Override
    @Transactional
    public NotificationDto deleteNotification(long id) {
        NotificationEntity existing = notificationRepository.findById(id)
                .orElseThrow(() -> new AppException(
                        "Notification not found", HttpStatus.NOT_FOUND
                ));

        NotificationDto dto = notificationMapper.toNotificationDto(existing);
        notificationRepository.delete(existing);

        return dto;
    }
}