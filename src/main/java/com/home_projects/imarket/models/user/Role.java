package com.home_projects.imarket.models.user;

import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.user.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "rolename", length = 8, nullable = false, unique = true)
    private RoleType roleName;

    private String description;

    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
                                     inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AuthorizedUser> users;
}
