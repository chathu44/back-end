package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDate;

public class EnrollmentDto {

    private Long id;
    private String enrollmentCode;
    private LocalDate enrollDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private ChildDto child;
    private ParentDto parent;
    private ProgramDto program;
    private ClassroomDto classroom;
    private StatusDto status;

    public EnrollmentDto() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEnrollmentCode() { return enrollmentCode; }
    public void setEnrollmentCode(String enrollmentCode) {
        this.enrollmentCode = enrollmentCode;
    }

    public LocalDate getEnrollDate() { return enrollDate; }
    public void setEnrollDate(LocalDate enrollDate) {
        this.enrollDate = enrollDate;
    }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

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

    public ParentDto getParent() { return parent; }
    public void setParent(ParentDto parent) { this.parent = parent; }

    @com.fasterxml.jackson.annotation.JsonSetter("parent")
    public void setParentFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.parent = null;
            return;
        }
        ParentDto dto = new ParentDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.parent = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            if (node.hasNonNull("fullName")) {
                dto.setFullName(node.get("fullName").asText());
            }

            this.parent = dto;
        }
    }

    public ProgramDto getProgram() { return program; }
    public void setProgram(ProgramDto program) { this.program = program; }

    @com.fasterxml.jackson.annotation.JsonSetter("program")
    public void setProgramFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.program = null;
            return;
        }
        ProgramDto dto = new ProgramDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.program = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }

            if (node.hasNonNull("ProgramName")) {
                dto.setProgramName(node.get("ProgramName").asText());
            }

            this.program = dto;
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