package com.bit.backend.controllers;

import com.bit.backend.dtos.ApiListResponse;
import com.bit.backend.dtos.NotificationDto;
import com.bit.backend.services.NotificationServiceI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
public class NotificationController {

    private final NotificationServiceI notificationServiceI;

    public NotificationController(NotificationServiceI notificationServiceI) {
        this.notificationServiceI = notificationServiceI;
    }

    @GetMapping("/notification")
    public ResponseEntity<ApiListResponse<NotificationDto>> getAllNotifications() {
        return ResponseEntity.ok(
                ApiListResponse.of(notificationServiceI.getAllNotifications())
        );
    }

    @GetMapping("/notification/{id}")
    public ResponseEntity<ApiListResponse<NotificationDto>> getNotificationById(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        notificationServiceI.getNotificationById(id)
                )
        );
    }

    @PostMapping("/notification")
    public ResponseEntity<ApiListResponse<NotificationDto>> addNotification(
            @RequestBody NotificationDto notificationDto) {
        NotificationDto created = notificationServiceI.addNotification(
                notificationDto
        );

        return ResponseEntity
                .created(URI.create("/api/v1/notification/" + created.getId()))
                .body(ApiListResponse.ofOne(created));
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<ApiListResponse<NotificationDto>> updateNotification(
            @PathVariable long id,
            @RequestBody NotificationDto notificationDto) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(
                        notificationServiceI.updateNotification(id, notificationDto)
                )
        );
    }

    @DeleteMapping("/notification/{id}")
    public ResponseEntity<ApiListResponse<NotificationDto>> deleteNotification(
            @PathVariable long id) {
        return ResponseEntity.ok(
                ApiListResponse.ofOne(notificationServiceI.deleteNotification(id))
        );
    }
}