package com.home_projects.imarket.dao.interfaces.impl;

import com.home_projects.imarket.models.BaseEntity;

import java.lang.reflect.ParameterizedType;

@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends BaseEntity> {

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
            return type.newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Cannot create instance of: " + type);
        }
    }
}
