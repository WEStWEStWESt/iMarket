package com.home_projects.imarket.facades.converters;

import com.home_projects.imarket.facades.converters.interfaces.Converter;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractConverter<S, T> implements Converter<S, T> {
    @Override
    public T convert(S s) {
        try {
            return convert(s, createTarget());
        } catch (Exception e) {
            throw new RuntimeException("Conversion error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private T createTarget() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        return (T) Class.forName(
                ((ParameterizedType) getClass().getGenericSuperclass())
                        .getActualTypeArguments()[1]
                        .getTypeName())
                .newInstance();
    }
}
