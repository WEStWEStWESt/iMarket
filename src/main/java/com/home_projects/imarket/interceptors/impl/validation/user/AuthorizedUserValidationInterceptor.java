package com.home_projects.imarket.interceptors.impl.validation.user;

import com.home_projects.imarket.interceptors.impl.validation.AbstractValidationInterceptor;
import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.user.AuthorizedUser;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthorizedUserValidationInterceptor extends AbstractValidationInterceptor {
    @Override
    public void execute(BaseEntity entity) {
        String typeName = entity.getClass().getTypeName();
        if (entity instanceof AuthorizedUser) {
            log.debug(typeName + " validation initiated...");
            validateField("username", "userName", entity);
            validateField("password", "password", entity);
            log.debug(typeName + " is valid.");
        }
    }
}
