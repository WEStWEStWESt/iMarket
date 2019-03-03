package com.home_projects.imarket.dao.interceptors.impl.validation;

import com.home_projects.imarket.exceptions.ValidationException;
import com.home_projects.imarket.dao.interceptors.InterceptorManager;
import com.home_projects.imarket.dao.interceptors.annotations.EntityInterceptor;
import com.home_projects.imarket.dao.interceptors.interfaces.ValidationInterceptor;
import com.home_projects.imarket.models.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.regex.Pattern;

@Slf4j
public abstract class AbstractValidationInterceptor implements ValidationInterceptor {
    @Autowired
    private InterceptorManager manager;

    protected <T extends BaseEntity> void validateFields(T entity, Pair<String, String>... pairs) {
        Class<? extends BaseEntity> type = entity.getClass();
        String typeName = type.getTypeName();
        String error;
        try {
            log.debug(typeName + " validation initiated...");
            if (type != getClass().getAnnotation(EntityInterceptor.class).interceptorEntity()) {
                error = "unexpected entity type - [" + typeName + "]";
                log.error(error);
                throw new ValidationException(error);
            }

            for (Pair<String, String> pair : pairs) {
                validateField(pair.getFirst(), pair.getSecond(), entity);
            }

            log.debug(typeName + " is valid.");
        } catch (Exception e) {
            error = "Validation error: " + e.getMessage();
            log.error(error);
            throw new ValidationException(error);
        }
    }

    protected Pair<String, String> arguments(String propertyName, String fieldName) {
        return Pair.of(propertyName, fieldName);
    }

    private <T extends BaseEntity> void validateField(String propertyName, String fieldName, T entity) throws IllegalAccessException {
        Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
        ReflectionUtils.makeAccessible(Objects.requireNonNull(field));
        String stringValue;

        stringValue = field.get(entity).toString();
        if (stringValue == null || stringValue.isEmpty()) {
            throw new IllegalArgumentException("the field is empty: " + fieldName);
        }

        if (!Pattern.compile(getPattern(propertyName)).matcher(stringValue).find()) {
            log.error(fieldName + " - incorrect value [" + stringValue + "]");
            throw new IllegalArgumentException("wrong field value: " + fieldName);
        }
        log.debug(fieldName + " is valid : " + stringValue);
    }

    private String getPattern(String propertyName) {
        final String DEFAULT = "default";

        return manager.getValidationProperties().getProperty(propertyName, DEFAULT);
    }
}
