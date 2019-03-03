package com.home_projects.imarket.dao.interceptors.interfaces;

import com.home_projects.imarket.models.BaseEntity;

public interface Interceptor {
    void execute(BaseEntity entity);
}
