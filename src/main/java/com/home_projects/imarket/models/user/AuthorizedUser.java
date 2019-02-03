package com.home_projects.imarket.models.user;

import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.profile.Profile;
import com.home_projects.imarket.models.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorized_users")
@EqualsAndHashCode(callSuper = true)
public class AuthorizedUser extends BaseEntity {

    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String userName;

    @Column(length = 12, nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

    @OneToOne(mappedBy = "user")
    private Profile profile;
}
