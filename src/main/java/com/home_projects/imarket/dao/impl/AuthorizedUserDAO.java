package com.home_projects.imarket.dao.impl;

import com.home_projects.imarket.dao.AbstractDAO;
import com.home_projects.imarket.models.user.AuthorizedUser;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorizedUserDAO extends AbstractDAO<AuthorizedUser> {
    public AuthorizedUser getByName(AuthorizedUser user) {
        return entityManager.createQuery("FROM " + getSimpleEntityName() + " t WHERE t.userName = :par_name", entityType())
                .setParameter("par_name", user.getUserName())
                .getSingleResult();
    }
}
