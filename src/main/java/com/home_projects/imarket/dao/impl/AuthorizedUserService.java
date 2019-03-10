package com.home_projects.imarket.dao.impl;

import com.home_projects.imarket.dao.AbstractDAO;
import com.home_projects.imarket.dao.interfaces.AuthorizedUserRepository;
import com.home_projects.imarket.models.user.AuthorizedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizedUserService extends AbstractDAO<AuthorizedUser> {
    @Autowired
    private AuthorizedUserRepository repository;

    public AuthorizedUser getByName(AuthorizedUser user) {
        return Optional.of(repository.findByUserName(user.getUserName())).orElse(null);
    }

    public AuthorizedUser getByNameAndPassword(AuthorizedUser user) {
        return Optional.of(repository.findByUserNameAndPassword(user.getUserName(), user.getPassword())).orElse(null);
    }

    @Override
    public AuthorizedUser save(AuthorizedUser user) {
        getInterceptorManager().onPrepare(user);
        getInterceptorManager().onValidate(user);
        return repository.save(user);
    }

    @Override
    public boolean delete(AuthorizedUser user) {
        getInterceptorManager().onRemove(user);
        repository.delete(user);
        return !repository.existsById(user.getId());
    }
}
