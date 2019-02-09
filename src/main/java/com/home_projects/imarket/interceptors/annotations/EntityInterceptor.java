package com.home_projects.imarket.interceptors.annotations;

import com.home_projects.imarket.models.BaseEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityInterceptor {
    Class<? extends BaseEntity> interceptorEntity();
}
