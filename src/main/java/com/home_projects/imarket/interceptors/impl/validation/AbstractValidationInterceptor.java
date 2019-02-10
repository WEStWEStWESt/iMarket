package com.home_projects.imarket.interceptors.impl.validation;

import com.home_projects.imarket.exceptions.ValidationException;
import com.home_projects.imarket.interceptors.InterceptorManager;
import com.home_projects.imarket.interceptors.interfaces.ValidationInterceptor;
import com.home_projects.imarket.models.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.util.regex.Pattern;

@Slf4j
public abstract class AbstractValidationInterceptor implements ValidationInterceptor {
    @Autowired
    private InterceptorManager manager;

    protected <T extends BaseEntity> boolean validateField(String propertyName, String fieldName, T entity) {

        String stringValue = null;
        try {
            stringValue = ReflectionUtils.findField(entity.getClass(), fieldName).get(entity).toString();
            if (stringValue == null || stringValue.isEmpty()){
                throw new IllegalArgumentException("the field is empty: " + fieldName);
            }

            if (!Pattern.compile(getPattern(propertyName)).matcher(stringValue).find()) {
                throw new IllegalArgumentException("wrong field value: " + fieldName);
            }
            log.debug(fieldName + " is valid : " + stringValue);
            return true;
        } catch (Exception e) {
            log.error(fieldName + " - " + e.getMessage() + " [" + stringValue + "]");
            throw new ValidationException( fieldName + " - " + e.getMessage() + " [" + stringValue + "]");
        }
    }

    private String getPattern(String propertyName) {
        final String DEFAULT = "default";

        return manager.getValidationProperties().getProperty(propertyName, DEFAULT);
    }

}
