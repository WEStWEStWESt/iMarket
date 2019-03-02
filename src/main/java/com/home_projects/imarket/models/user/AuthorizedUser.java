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
@EqualsAndHashCode(callSuper = true)
public class AuthorizedUser extends BaseEntity {

    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private Set<Role> roles;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Profile profile;
}
