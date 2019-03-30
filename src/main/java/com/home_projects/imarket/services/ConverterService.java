package com.home_projects.imarket.services;

import com.home_projects.imarket.facades.converters.interfaces.Converter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

@Service
@SuppressWarnings("ALL")
public class ConverterService implements ApplicationListener<ApplicationReadyEvent> {
    private static Map<String, Converter> converters;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        converters = applicationReadyEvent.getApplicationContext().getBeansOfType(Converter.class);
    }

    public <S, T> Converter<S, T> getConverterFor(Class<S> source, Class<T> target) {
        return converters.values()
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
