package com.home_projects.imarket.dao.impl;

import com.home_projects.imarket.dao.AbstractDAO;
import com.home_projects.imarket.models.user.AuthorizedUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
public class AuthorizedUserDAO extends AbstractDAO<AuthorizedUser> {
    public User getByName(String username) {
        AuthorizedUser user = entityManager.createQuery("FROM " + getSimpleEntityName() + " t WHERE t.userName = :par_name", AuthorizedUser.class)
                .setParameter("par_name", username)
                .getSingleResult();
        return new User(user.getUserName(), user.getPassword(),
                user.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority(
                                role.getRoleName()
                                        .name()))
                        .collect(Collectors.toSet()));
    }
}
