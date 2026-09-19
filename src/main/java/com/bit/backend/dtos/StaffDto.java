package com.bit.backend.dtos;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;

public class StaffDto {

    private Long id;
    private String staffCode;
    private UserDto user;
    private String fullName;
    private String role;
    private String phone;
    private String nic;
    private LocalDate hireDate;
    private ClassroomDto classroom;
    private StatusDto status;

    public StaffDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
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

            if (node.hasNonNull("firstName")) {
                dto.setFirstName(node.get("firstName").asText());
            }

            this.user = dto;
        }
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public ClassroomDto getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomDto classroom) {
        this.classroom = classroom;
    }

    @com.fasterxml.jackson.annotation.JsonSetter("classroom")
    public void setClassroomFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.classroom = null;
            return;
        }
        ClassroomDto dto = new ClassroomDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.classroom = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            if (node.hasNonNull("RoomName")) {
                dto.setRoomName(node.get("RoomName").asText());
            }

            this.classroom = dto;
        }
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
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