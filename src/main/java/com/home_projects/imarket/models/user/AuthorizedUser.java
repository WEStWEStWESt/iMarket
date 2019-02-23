package com.home_projects.imarket.models.user;

import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.profile.Profile;
import com.home_projects.imarket.models.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorized_users")
@ToString(exclude = {"roles", "profile"})
@EqualsAndHashCode(callSuper = true, exclude = {"roles", "profile"})
public class AuthorizedUser extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Role> roles;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Profile profile;
}
