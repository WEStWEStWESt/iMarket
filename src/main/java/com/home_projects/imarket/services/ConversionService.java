package com.home_projects.imarket.services;

import com.home_projects.imarket.facades.converters.interfaces.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@Service
@SuppressWarnings("ALL")
public class ConversionService {

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
