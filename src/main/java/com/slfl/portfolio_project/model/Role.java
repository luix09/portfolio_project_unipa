package com.slfl.portfolio_project.model;

import com.slfl.portfolio_project.model.user.User;
import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.core.GrantedAuthority;

import java.util.Set;

@Entity
@Table(name = "roles")
@EnableAutoConfiguration
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Integer roleId;

    @Column
    @Enumerated(EnumType.STRING)
    private RoleType roleName;

    @OneToMany(mappedBy = "role")
    private Set<User> users;

    public Role() {
        super();
    }

    public Role(RoleType roleName) {
        this.roleName = roleName;
    }

    public Role(String role) {
        RoleType type = RoleType.valueOf(role);
        this.roleId = type.ordinal();
        this.roleName = type;
    }

    @Override
    public String getAuthority() {
        return this.roleName.name();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}