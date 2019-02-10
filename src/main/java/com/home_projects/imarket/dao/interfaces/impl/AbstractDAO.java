package com.home_projects.imarket.dao.interfaces.impl;

import com.home_projects.imarket.interceptors.InterceptorManager;
import com.home_projects.imarket.models.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends BaseEntity> {
    @Autowired private InterceptorManager interceptorManager;

    Class<T> entityType() {
        String typeName = ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0]
                .getTypeName();
        try {
            return (Class<T>) Class.forName(typeName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unknown entity type name: " + typeName);
        }
    }

    T create(){
        Class<T> type = entityType();
        try {
            T t = type.newInstance();
            interceptorManager.onInit(t);
            return t;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Cannot create instance of: " + type);
        }
    }

    public abstract T save(T t);
    public abstract boolean delete(T t);
}
