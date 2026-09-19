package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AttendanceDto {

    private Long id;
    private LocalDate attendanceDate;
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;
    private StaffDto checkedInBy;
    private StaffDto checkedOutBy;
    private String pickupPerson;
    private String notes;
    private ChildDto child;
    private ClassroomDto classroom;
    private StatusDto status;

    public AttendanceDto() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public LocalDateTime getCheckInTime() { return checkInTime; }
    public void setCheckInTime(LocalDateTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalDateTime getCheckOutTime() { return checkOutTime; }
    public void setCheckOutTime(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public StaffDto getCheckedInBy() { return checkedInBy; }
    public void setCheckedInBy(StaffDto checkedInBy) {
        this.checkedInBy = checkedInBy;
    }

    @com.fasterxml.jackson.annotation.JsonSetter("checkedInBy")
    public void setCheckedInByFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.checkedInBy = null;
            return;
        }
        StaffDto dto = new StaffDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.checkedInBy = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            if (node.hasNonNull("fullName")) {
                dto.setFullName(node.get("fullName").asText());
            }

            this.checkedInBy = dto;
        }
    }

    public StaffDto getCheckedOutBy() { return checkedOutBy; }
    public void setCheckedOutBy(StaffDto checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    @com.fasterxml.jackson.annotation.JsonSetter("checkedOutBy")
    public void setCheckedOutByFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.checkedOutBy = null;
            return;
        }
        StaffDto dto = new StaffDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.checkedOutBy = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            if (node.hasNonNull("fullName")) {
                dto.setFullName(node.get("fullName").asText());
            }

            this.checkedOutBy = dto;
        }
    }

    public String getPickupPerson() { return pickupPerson; }
    public void setPickupPerson(String pickupPerson) {
        this.pickupPerson = pickupPerson;
    }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public ChildDto getChild() { return child; }
    public void setChild(ChildDto child) { this.child = child; }

    @com.fasterxml.jackson.annotation.JsonSetter("child")
    public void setChildFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.child = null;
            return;
        }
        ChildDto dto = new ChildDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.child = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            if (node.hasNonNull("firstName")) {
                dto.setFirstName(node.get("firstName").asText());
            }

            this.child = dto;
        }
    }

    public ClassroomDto getClassroom() { return classroom; }
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

    public StatusDto getStatus() { return status; }
    public void setStatus(StatusDto status) { this.status = status; }

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