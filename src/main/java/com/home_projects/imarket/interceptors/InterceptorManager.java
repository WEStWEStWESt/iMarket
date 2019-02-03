package com.home_projects.imarket.interceptors;

import com.home_projects.imarket.models.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InterceptorManager {
    @Autowired private ApplicationContext context;

    public void onInit(BaseEntity entity) {
        log.info(entity.getClass().getTypeName() + " initialization started...");
    }

    public void onPrepare(BaseEntity entity) {
        log.info(entity.getClass().getTypeName() + " preparing initiated...");
    }

    public void onValidate(BaseEntity entity) {
        log.info(entity.getClass().getTypeName() + " validation initiated...");
    }

    public void onRemove(BaseEntity entity) {
        log.info(entity.getClass().getTypeName() + " removing initiated...");
    }

    // TODO add method that return interceptors for class interceptFor(Class<? extends Interceptor> type)
}
