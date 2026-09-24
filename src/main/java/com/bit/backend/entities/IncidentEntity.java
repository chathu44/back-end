package com.bit.backend.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "incident")
public class IncidentEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "incident_code", length = 50)
    private String incidentCode;

    @Column(name = "incident_date", nullable = false)
    private LocalDateTime incidentDate;

    @Column(name = "incident_type", length = 50)
    private String incidentType;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "action_taken", columnDefinition = "TEXT")
    private String actionTaken;

    @Column(name = "parent_notified")
    private Boolean parentNotified;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "child_id", nullable = false)
    private ChildEntity child;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classroom_id", nullable = false)
    private ClassroomEntity classroom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reported_by", nullable = false)
    private StaffEntity reportedBy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusEntity status;

    public IncidentEntity() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIncidentCode() { return incidentCode; }
    public void setIncidentCode(String incidentCode) { this.incidentCode = incidentCode; }
    public LocalDateTime getIncidentDate() { return incidentDate; }
    public void setIncidentDate(LocalDateTime incidentDate) { this.incidentDate = incidentDate; }
    public String getIncidentType() { return incidentType; }
    public void setIncidentType(String incidentType) { this.incidentType = incidentType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getActionTaken() { return actionTaken; }
    public void setActionTaken(String actionTaken) { this.actionTaken = actionTaken; }
    public Boolean getParentNotified() { return parentNotified; }
    public void setParentNotified(Boolean parentNotified) { this.parentNotified = parentNotified; }
    public ChildEntity getChild() { return child; }
    public void setChild(ChildEntity child) { this.child = child; }
    public ClassroomEntity getClassroom() { return classroom; }
    public void setClassroom(ClassroomEntity classroom) { this.classroom = classroom; }
    public StaffEntity getReportedBy() { return reportedBy; }
    public void setReportedBy(StaffEntity reportedBy) { this.reportedBy = reportedBy; }
    public StatusEntity getStatus() { return status; }
    public void setStatus(StatusEntity status) { this.status = status; }
}