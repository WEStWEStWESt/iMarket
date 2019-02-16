package com.home_projects.imarket.dao.interfaces.impl;

import com.home_projects.imarket.interceptors.InterceptorManager;
import com.home_projects.imarket.models.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;

@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends BaseEntity> {

    @PersistenceContext private EntityManager entityManager;
    @Autowired private InterceptorManager interceptorManager;

    @Getter private Class<T> entityType;
    @Getter(AccessLevel.PROTECTED) private String tableName;

    @PostConstruct
    public void init(){
        entityType = entityType();
        tableName = tableName();
    }

    private Class<T> entityType() {
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

    private String tableName(){
       return entityType.getAnnotation(Table.class).name();
    }

    public T create(){
        Class<T> type = entityType();
        try {
            T t = type.newInstance();
            interceptorManager.onInit(t);
            return t;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Cannot create instance of: " + type);
        }
    }

    public T getOne(Long id){
       return (T) entityManager.createQuery("FROM " + entityType + " WHERE id = " + id).getSingleResult();
    }

    public abstract T save(T t);
    public abstract boolean delete(T t);
}
