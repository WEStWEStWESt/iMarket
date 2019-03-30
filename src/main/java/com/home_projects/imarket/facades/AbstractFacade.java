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
import java.util.Set;
import java.util.stream.Collectors;

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
        Converter<E, D> converter = converterService.getConverterFor(entityType, dtoType);
        return modelService.getAll(entityType, ids)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public D save(D dto) {
        E actualEntity = getActualEntity(dto);
        E saved = modelService.save(entityType, actualEntity);
        return convertToActualDto(saved);
    }

    @Override
    public List<D> saveAll(List<D> dtos) {
        List<E> actualEntities = dtos.stream()
                .map(this::getActualEntity)
                .collect(Collectors.toList());
        return modelService.saveAll(entityType, actualEntities)
                .stream()
                .map(this::convertToActualDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean delete(D dto) {
        return delete(dto.getId());
    }

    @Override
    public boolean delete(Long id) {
        return modelService.delete(entityType, id);
    }

    @Override
    public boolean deleteAll(List<Long> ids) {
        return modelService.deleteAll(entityType, ids);
    }

    @Override
    public boolean deleteAll() {
        return modelService.deleteAll(entityType);
    }

    protected <E extends BaseEntity, D extends BaseDTO> Optional<E> getActualForeignKey(Class<E> type, D dto) {
        return Optional.of(modelService.getOne(type, dto.getId()));
    }

    protected <E extends BaseEntity, D extends BaseDTO> Optional<Set<E>> getActualForeignKeys(Class<E> type, Set<D> dtos) {
        List<Long> ids = dtos.stream()
                .map(BaseDTO::getId)
                .collect(Collectors.toList());
        return Optional.of(modelService.getAll(type, ids)
                .stream()
                .collect(Collectors.toSet()));
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

    protected D convertToActualDto(E e) {
        return converterService.getConverterFor(entityType, dtoType).convert(e);
    }

    protected E getActualEntity(D dto) {
        return convertToActualEntity(dto);
    }

    private E getActualById(Long id) {
        return Optional.of(modelService.getOne(entityType, id))
                .orElseThrow(() -> new RuntimeException(entityType.getSimpleName() + " with id: " + id + " not found"));
    }
}
