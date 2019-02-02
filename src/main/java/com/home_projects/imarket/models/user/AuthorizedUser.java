package com.home_projects.imarket.models.user;

import com.home_projects.imarket.models.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorized_users")
@EqualsAndHashCode(callSuper = true)
public class AuthorizedUser extends BaseEntity {

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 12, nullable = false)
    private String password;

    @ManyToMany(mappedBy = "users")
    private Set<Role> roles;

}
