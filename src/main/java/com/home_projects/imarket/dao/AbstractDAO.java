package com.home_projects.imarket.dao;

import com.home_projects.imarket.dao.interceptors.InterceptorManager;
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

    public Class<T> entityType() {
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

    public String getSimpleEntityName() {
        return entityType.getSimpleName();
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
        return (T) entityManager.createQuery("FROM " + getSimpleEntityName() + " t WHERE t.id = " + id, entityType).getSingleResult();
    }

    public List<T> getAll(List<Long> ids) {
        return entityManager.createQuery("FROM " + getSimpleEntityName() + " t WHERE t.id IN ("
                + ids.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", ")) + ")", entityType)
                .getResultList();
    }

    public T save(T t) {
        interceptorManager.onPrepare(t);
        interceptorManager.onValidate(t);
        entityManager.persist(t);
        return t;
    }

    public boolean delete(T t) {
        interceptorManager.onRemove(t);
        entityManager.remove(t);
        return getOne(t.getId()) == null;
    }

    public boolean delete(Long id) {
        return entityManager.createQuery("DELETE FROM " + getSimpleEntityName() + " t WHERE t.id = " + id, entityType).executeUpdate() == 1;
    }

    public boolean deleteAll(List<Long> ids) {
        return entityManager.createQuery("DELETE FROM " + getSimpleEntityName() + " t WHERE t.id IN ("
                + ids.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(", ")) + ")", entityType)
                .executeUpdate() == ids.size();
    }

    public boolean deleteAll() {
        entityManager.createQuery("DELETE FROM " + getSimpleEntityName())
                .executeUpdate();
        return entityManager.createQuery("SELECT COUNT(t) FROM " + getSimpleEntityName() + " t", Long.class)
                .getSingleResult() == 0;
    }
}
