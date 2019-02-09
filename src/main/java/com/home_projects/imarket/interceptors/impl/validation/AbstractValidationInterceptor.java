package com.home_projects.imarket.interceptors.impl.validation;

import com.home_projects.imarket.interceptors.InterceptorManager;
import com.home_projects.imarket.interceptors.interfaces.ValidationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.Properties;
import java.util.regex.Pattern;

public abstract class AbstractValidationInterceptor implements ValidationInterceptor {
    private Properties properties;
    @Autowired
    private InterceptorManager manager;

    protected <T> boolean validateField(String propertyName, Field field, Class<T> fieldType) throws IllegalAccessException, InstantiationException {
        String stringValue = field.get(fieldType.newInstance()).toString();
        return Pattern.compile(getPattern(propertyName)).matcher(stringValue).find();
    }

    private String getPattern(String propertyName) {
        if (properties == null) {
            properties = manager.getValidationProperties();
        }
        final String DEFAULT = "default";

        return properties.getProperty(propertyName, DEFAULT);
    }
}
