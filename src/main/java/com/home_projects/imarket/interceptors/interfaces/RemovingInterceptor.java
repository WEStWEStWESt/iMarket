package com.home_projects.imarket.interceptors.interfaces;

import com.home_projects.imarket.models.BaseEntity;

public interface RemovingInterceptor extends Interceptor {
    void remove(BaseEntity entity);
}
