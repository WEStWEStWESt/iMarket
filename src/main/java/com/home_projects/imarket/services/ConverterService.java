package com.home_projects.imarket.services;

import com.home_projects.imarket.facades.converters.interfaces.Converter;
import com.home_projects.imarket.facades.dto.BaseDTO;
import com.home_projects.imarket.models.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("ALL")
public class ConverterService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ModelService modelService;

    public <S, T> Converter<S, T> getConverterFor(Class<S> source, Class<T> target) {
        return context.getBeansOfType(Converter.class)
                .values()
                .stream()
                .filter(converter -> {
                    Type[] actualTypeArguments = ((ParameterizedType) converter.getClass().getGenericSuperclass()).getActualTypeArguments();
                    return isMatch(actualTypeArguments, source, target);
                })
                .findFirst()
                .orElse(null);
    }

    public <D extends BaseDTO, E extends BaseEntity> D getOne(Long id, Class<D> dto, Class<E> entity) {
        Converter<E, D> converter = getConverterFor(entity, dto);
        return converter.convert(modelService.getOne(entity, id));
    }

    public <D extends BaseDTO, E extends BaseEntity> List<D> getAll(List<Long> ids, Class<D> dto, Class<E> entity) {
        Converter<E, D> converter = getConverterFor(entity, dto);
        return modelService.getAll(entity, ids)
                .stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    public <D extends BaseDTO, E extends BaseEntity> D save(D d, Class<D> dto, Class<E> entity) {
        return saveAll(Collections.singletonList(d), dto, entity).get(0);
    }

    public <D extends BaseDTO, E extends BaseEntity> List<D> saveAll(List<D> ds, Class<D> dto, Class<E> entity) {
        Converter<D, E> converter = getConverterFor(dto, entity);
        Converter<E, D> reversedConverter = getConverterFor(entity, dto);

        return ds.stream()
                .map(d -> {
                    E actualEntity = getActualEntity(d.getId(), entity);
                    E convertedEntity = converter.convert(d, actualEntity);
                    E savedEntity = modelService.save(entity, convertedEntity);
                    return reversedConverter.convert(savedEntity);
                }).collect(Collectors.toList());
    }

    public <D extends BaseDTO, E extends BaseEntity> boolean delete(D d, Class<D> dto, Class<E> entity) {
        Converter<D, E> converter = getConverterFor(dto, entity);
        return modelService.delete(entity, converter.convert(d));
    }

    private <D extends BaseDTO, E extends BaseEntity> E getActualEntity(Long id, Class<E> entity) {
        E e = null;
        if (id == null) {
            e = modelService.create(entity);
        } else {
            e = modelService.getOne(entity, id);
        }
        return e;
    }

    private <S, T> boolean isMatch(Type[] types, Class<S> source, Class<T> target) {
        Pair<Class<?>, Class<?>> pair = cast(types[0].getTypeName(), types[1].getTypeName());
        boolean firstMatch = pair.getFirst() == source;
        boolean secondMatch = pair.getSecond() == target;
        return firstMatch && secondMatch;
    }

    private Pair<Class<?>, Class<?>> cast(String sourceTypeName, String targetTypeName) {
        try {
            return Pair.of(Class.forName(sourceTypeName), Class.forName(targetTypeName));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unknown parametrized type");
        }
    }
}
