package com.bit.backend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "auth_group_users")
public class PrivilegeGroupUser extends AuditableEntity {

    public PrivilegeGroupUser() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auth_group_id", nullable = false)
    private PrivilegeGroup authGroupId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userId;

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

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }
}
