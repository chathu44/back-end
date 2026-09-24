package com.bit.backend.dtos;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.LocalDateTime;

public class IncidentDto {

    private Long id;
    private String incidentCode;
    private LocalDateTime incidentDate;
    private String incidentType;
    private String description;
    private String actionTaken;
    private Boolean parentNotified;
    private ChildDto child;
    private ClassroomDto classroom;
    private StaffDto reportedBy;
    private StatusDto status;

    public IncidentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIncidentCode() {
        return incidentCode;
    }

    public void setIncidentCode(String incidentCode) {
        this.incidentCode = incidentCode;
    }

    public LocalDateTime getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(LocalDateTime incidentDate) {
        this.incidentDate = incidentDate;
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Boolean getParentNotified() {
        return parentNotified;
    }

    public void setParentNotified(Boolean parentNotified) {
        this.parentNotified = parentNotified;
    }

    public ChildDto getChild() {
        return child;
    }

    public void setChild(ChildDto child) {
        this.child = child;
    }

    public ClassroomDto getClassroom() {
        return classroom;
    }

    public void setClassroom(ClassroomDto classroom) {
        this.classroom = classroom;
    }

    public StaffDto getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(StaffDto reportedBy) {
        this.reportedBy = reportedBy;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }

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
            this.child = dto;
        }
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
            this.classroom = dto;
        }
    }

    @com.fasterxml.jackson.annotation.JsonSetter("reportedBy")
    public void setReportedByFromJson(JsonNode node) {
        if (node == null || node.isNull()) {
            this.reportedBy = null;
            return;
        }

        StaffDto dto = new StaffDto();

        if (node.isNumber() || node.isTextual()) {
            dto.setId(node.asLong());
            this.reportedBy = dto;
            return;
        }

        if (node.isObject()) {
            if (node.hasNonNull("id")) {
                dto.setId(node.get("id").asLong());
            }
            this.reportedBy = dto;
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