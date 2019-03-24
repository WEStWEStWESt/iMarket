package com.home_projects.imarket.facades;

import com.home_projects.imarket.facades.converters.interfaces.Converter;
import com.home_projects.imarket.facades.dto.BaseDTO;
import com.home_projects.imarket.facades.interfaces.Facade;
import com.home_projects.imarket.models.BaseEntity;
import com.home_projects.imarket.services.ConverterService;
import com.home_projects.imarket.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("ALL")
public abstract class AbstractFacade<E extends BaseEntity, D extends BaseDTO> implements Facade<D> {
    @Autowired
    protected ModelService modelService;
    @Autowired
    protected ConverterService converterService;

    protected Class<E> entityType;

    protected Class<D> dtoType;

    @PostConstruct
    public void init() {
        Type[] types = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
        try {
            entityType = (Class<E>) Class.forName(types[0].getTypeName());
            dtoType = (Class<D>) Class.forName(types[1].getTypeName());
        } catch (ClassNotFoundException e) {
           throw new RuntimeException("Unexpected generic type: " + e.getMessage());
        }
    }

    @Override
    public D getOne(Long id) {
        E e = getActualById(id);
        Converter<E, D> converter = converterService.getConverterFor(entityType, dtoType);

        return converter.convert(e);
    }

    @Override
    public List<D> getAll(List<Long> ids) {
        return null;
    }

    @Override
    public D save(D dto) {
        return null;
    }

    @Override
    public boolean delete(D dto) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean deleteAll(List<Long> ids) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    protected E convertToActualEntity(D dto) {
        E e = null;
        Long id = dto.getId();
        if (id == null) {
            e = (E) modelService.create(entityType);
        } else {
            e = (E) getActualById(id);
        }

        return converterService.getConverterFor(dtoType, entityType).convert(dto, e);
    }

    protected E getActualEntity(D dto) {
        return convertToActualEntity(dto);
    }

    private E getActualById(Long id) {
        return Optional.of(modelService.getOne(entityType, id))
                .orElseThrow(() -> new RuntimeException(entityType.getSimpleName() + " with id: " + id + " not found"));
    }
}
