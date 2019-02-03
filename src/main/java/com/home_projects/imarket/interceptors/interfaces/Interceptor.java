package com.home_projects.imarket.interceptors.interfaces;

import com.home_projects.imarket.models.BaseEntity;

public interface Interceptor {
    void execute(BaseEntity entity);
}
