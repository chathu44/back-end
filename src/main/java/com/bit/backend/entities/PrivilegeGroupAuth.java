package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "auth_group_authentication")
public class PrivilegeGroupAuth extends AuditableEntity {

    public PrivilegeGroupAuth() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auth_group_id", nullable = false)
    private PrivilegeGroup authGroupId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
            name = "auth_id",
            referencedColumnName = "auth_id",
            nullable = false
    )
    private Privilege authId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PrivilegeGroup getAuthGroupId() {
        return authGroupId;
    }

    public void setAuthGroupId(PrivilegeGroup authGroupId) {
        this.authGroupId = authGroupId;
    }

    public Privilege getAuthId() {
        return authId;
    }

    public void setAuthId(Privilege authId) {
        this.authId = authId;
    }
}