package com.home_projects.imarket.dao.interceptors;

import com.home_projects.imarket.dao.interceptors.annotations.EntityInterceptor;
import com.home_projects.imarket.dao.interceptors.interfaces.*;
import com.home_projects.imarket.models.BaseEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Properties;

@Component
public class InterceptorManager {
    @Getter private Properties validationProperties;
    @Autowired private ApplicationContext context;

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

    private <I extends Interceptor> void execute(BaseEntity entity, Class<I> type) {
        context.getBeansOfType(type)
                .values()
                .stream()
                .filter(interceptor -> entity.getClass() == interceptor.getClass()
                        .getAnnotation(EntityInterceptor.class)
                        .interceptorEntity())
                .forEach(interceptor -> interceptor.execute(entity));
    }

    @PostConstruct
    private void resolveProperties() {
        String propertyFile = "properties/validation.properties";
        validationProperties = new Properties();
        try {
            validationProperties.load((Objects.requireNonNull(context.getClassLoader())).getResourceAsStream(propertyFile));
        } catch (Exception e) {
            throw new RuntimeException("Validation properties not found: " + propertyFile);
        }
    }
}