package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "system_authentications")
public class Privilege extends AuditableEntity {

    public Privilege() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth_description", nullable = false, length = 255)
    private String authDescription;

    @Column(name = "assigned", columnDefinition = "INT DEFAULT 0")
    private int assigned;

    @Column(name = "auth_id", unique = true)
    private int authId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthDescription() {
        return authDescription;
    }

    public void setAuthDescription(String authDescription) {
        this.authDescription = authDescription;
    }

    public int getAssigned() {
        return assigned;
    }

    public void setAssigned(int assigned) {
        this.assigned = assigned;
    }

    public int getAuthId() {
        return authId;
    }

    public void setAuthId(int authId) {
        this.authId = authId;
    }
}
