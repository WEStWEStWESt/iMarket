package com.home_projects.imarket.dao.interceptors.impl.validation.user;

import com.home_projects.imarket.dao.interceptors.annotations.EntityInterceptor;
import com.home_projects.imarket.dao.interceptors.impl.validation.AbstractValidationInterceptor;
import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.models.user.AuthorizedUser;

@EntityInterceptor(interceptorEntity = AuthorizedUser.class)
public class AuthorizedUserValidationInterceptor extends AbstractValidationInterceptor {
    @Override
    public void execute(BaseEntity entity) {
        validateFields(entity, arguments("username", "userName"),
                               arguments("password", "password"));
    }
}
