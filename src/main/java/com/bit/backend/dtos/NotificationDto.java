package com.bit.backend.dtos;

import java.time.LocalDateTime;

public class NotificationDto {

    private Long id;
    private Long recipientId;
    private String recipientType;
    private String message;
    private String type;
    private LocalDateTime sentDate;
    private Boolean readStatus;

    public NotificationDto() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRecipientId() { return recipientId; }
    public void setRecipientId(Long recipientId) { this.recipientId = recipientId; }
    public String getRecipientType() { return recipientType; }
    public void setRecipientType(String recipientType) { this.recipientType = recipientType; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDateTime getSentDate() { return sentDate; }
    public void setSentDate(LocalDateTime sentDate) { this.sentDate = sentDate; }
    public Boolean getReadStatus() { return readStatus; }
    public void setReadStatus(Boolean readStatus) { this.readStatus = readStatus; }
}