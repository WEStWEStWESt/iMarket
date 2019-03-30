package com.home_projects.imarket.facades.converters;

import com.home_projects.imarket.facades.converters.interfaces.Converter;
import org.springframework.lang.Nullable;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import java.util.function.Consumer;

public abstract class AbstractConverter<S, T> implements Converter<S, T> {
    @Override
    public T convert(S s) {
        try {
            return convert(s, createTarget());
        } catch (Exception e) {
            throw new RuntimeException("Conversion error: " + e.getMessage());
        }
    }

    protected <A> void setIfPresent(@Nullable A argument, Consumer<A> consumer) {
        Optional.ofNullable(argument).ifPresent(consumer);
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
