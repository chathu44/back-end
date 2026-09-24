package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

public class AdminDto {

    private Long id;
    private String adminCode;
    private UserDto user;
    private String phone;
    private StatusDto status;

    public AdminDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String adminCode) {
        this.adminCode = adminCode;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

    @com.fasterxml.jackson.annotation.JsonSetter("user")
    public void setUserFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.user = null;
            return;
        }

        UserDto dto = new UserDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.user = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            this.user = dto;
        }
    }

    @com.fasterxml.jackson.annotation.JsonSetter("status")
    public void setStatusFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.status = null;
            return;
        }

        StatusDto dto = new StatusDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.status = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            if (node.hasNonNull("name")) {
                dto.setName(node.get("name").asText());
            }
            this.status = dto;
        }
    }
}