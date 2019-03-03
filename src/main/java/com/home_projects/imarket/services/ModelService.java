package com.home_projects.imarket.services;

import com.home_projects.imarket.dao.AbstractDAO;
import com.home_projects.imarket.models.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("unchecked")
public class ModelService {

    @Autowired
    private ApplicationContext context;

    public <E extends BaseEntity> AbstractDAO<E> getDAOFor(Class<E> type) {
        return context.getBeansOfType(AbstractDAO.class)
                .values()
                .stream()
                .filter(abstractDAO -> abstractDAO.getEntityType() == type)
                .findFirst()
                .orElse(null);
    }

    public <E extends BaseEntity> E create(Class<E> type) {
        return getDAOFor(type).create();
    }

    public <E extends BaseEntity> E getOne(Class<E> type, Long id) {
        return getDAOFor(type).getOne(id);
    }

    public <E extends BaseEntity> List<E> getAll(Class<E> type, List<Long> ids) {
        return getDAOFor(type).getAll(ids);
    }

    public <E extends BaseEntity> E save(Class<E> type, E e) {
        return getDAOFor(type).save(e);
    }

    public <E extends BaseEntity> List<E> saveAll(Class<E> type, List<E> es) {
        AbstractDAO<E> dao = getDAOFor(type);
        return es.stream()
                .map(dao::save)
                .collect(Collectors.toList());
    }

    public <E extends BaseEntity> boolean delete(Class<E> type, Long id) {
        return getDAOFor(type).delete(id);
    }

    public <E extends BaseEntity> boolean delete(Class<E> type, E e) {
        return getDAOFor(type).delete(e);
    }

    public <E extends BaseEntity> boolean deleteAll(Class<E> type, List<Long> ids) {
        return getDAOFor(type).deleteAll(ids);
    }

    public <E extends BaseEntity> boolean deleteAll(Class<E> type) {
        return getDAOFor(type).deleteAll();
    }
}
