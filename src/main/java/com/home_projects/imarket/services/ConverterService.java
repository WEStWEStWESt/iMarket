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
public class ConverterService {

    @Autowired
    private ApplicationContext context;

    public <S, T> Converter<S, T> getConverterFor(Class<S> source, Class<T> target) {
        return context.getBeansOfType(Converter.class)
                .values()
                .stream()
                .filter(converter -> {
                    Type[] actualTypeArguments = ((ParameterizedType) converter.getClass().getGenericSuperclass()).getActualTypeArguments();
                    try {
                        return isMatch(actualTypeArguments, source, target);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException("Unknown generic types");
                    }
                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Required <" + source.getSimpleName() + ", " + target.getSimpleName() + "> converter not found"));
    }

    private <S, T> boolean isMatch(Type[] types, Class<S> source, Class<T> target) throws ClassNotFoundException {
        boolean firstMatch = Class.forName(types[0].getTypeName()) == source;
        boolean secondMatch = Class.forName(types[1].getTypeName()) == target;
        return firstMatch && secondMatch;
    }
}
