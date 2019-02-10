package com.home_projects.imarket.dao.interfaces.impl;

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
        return repository.save(user);
    }

    @Override
    public boolean delete(AuthorizedUser user) {
        repository.delete(user);
        return repository.getOne(user.getId()) == null;
    }
}
