package com.home_projects.imarket.dao;

import com.home_projects.imarket.interceptors.InterceptorManager;
import com.home_projects.imarket.models.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@SuppressWarnings("All")
public abstract class AbstractDAO<T extends BaseEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    @Getter(AccessLevel.PROTECTED)
    private InterceptorManager interceptorManager;

    @Getter
    private Class<T> entityType;

    @Getter(AccessLevel.PROTECTED)
    private String tableName;

    @PostConstruct
    public void init() {
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

    private String tableName() {
        return entityType.getAnnotation(Table.class).name();
    }

    public T create() {
        Class<T> type = entityType();
        try {
            T t = type.newInstance();
            interceptorManager.onInit(t);
            return t;
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException("Cannot create instance of: " + type);
        }
    }

    public T getOne(Long id) {
        return (T) entityManager.createQuery("FROM " + entityType.getSimpleName() + " WHERE id = " + id).getSingleResult();
    }

    public List<T> getAll(List<Long> ids) {
        return entityManager.createNativeQuery("SELECT * FROM " + tableName + " WHERE id IN ("
                + ids.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", ")) + ")", entityType)
                .getResultList();
    }

    public abstract T save(T t);

    public abstract boolean delete(T t);

    public boolean delete(Long id) {
        return entityManager.createQuery("DELETE FROM " + entityType.getSimpleName() + " WHERE id = " + id).executeUpdate() == 1;
    }

    public boolean deleteAll(List<Long> ids) {
        return entityManager.createNativeQuery("DELETE FROM " + tableName + " WHERE id IN ("
                + ids.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", ")) + ")")
                .executeUpdate() == ids.size();
    }
}
