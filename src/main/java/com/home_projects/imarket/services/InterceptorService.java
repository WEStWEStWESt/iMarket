package com.home_projects.imarket.services;

import com.home_projects.imarket.dao.interceptors.annotations.EntityInterceptor;
import com.home_projects.imarket.dao.interceptors.interfaces.*;
import com.home_projects.imarket.models.BaseEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

@Component
public class InterceptorService {
    @Getter
    private Properties validationProperties;
    @Autowired
    private ApplicationContext context;
    private Map<String, Interceptor> interceptors;

    public void onInit(BaseEntity entity) {
        execute(entity, InitializationInterceptor.class);
    }

    public void onPrepare(BaseEntity entity) {
        execute(entity, PreparingInterceptor.class);
    }

    public void onValidate(BaseEntity entity) {
        execute(entity, ValidationInterceptor.class);
    }

    public void onRemove(BaseEntity entity) {
        execute(entity, RemovingInterceptor.class);
    }

    private <I extends Interceptor> void execute(BaseEntity entity, Class<I> lifecycle) {
        interceptors.values()
                .stream()
                .filter(interceptor -> {
                    Class<? extends Interceptor> type = interceptor.getClass();
                    if (Arrays.asList(type.getSuperclass().getInterfaces()).contains(lifecycle)) {
                        return entity.getClass() == type.getAnnotation(EntityInterceptor.class)
                                .interceptorEntity();
                    } else return false;
                })
                .forEach(interceptor -> interceptor.execute(entity));
    }

    @PostConstruct
    private void init() {
        String propertyFile = "properties/validation.properties";
        validationProperties = new Properties();
        interceptors = context.getBeansOfType(Interceptor.class);
        try {
            validationProperties.load((Objects.requireNonNull(context.getClassLoader())).getResourceAsStream(propertyFile));
        } catch (Exception e) {
            throw new RuntimeException("Validation properties not found: " + propertyFile);
        }
    }
}
