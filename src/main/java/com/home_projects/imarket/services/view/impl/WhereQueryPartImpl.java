package com.home_projects.imarket.services.view.impl;

import com.home_projects.imarket.services.view.QueryPart;
import com.home_projects.imarket.services.view.WhereQueryPart;
import org.springframework.stereotype.Component;

@Component
public class WhereQueryPartImpl implements WhereQueryPart{

    @Override
    public QueryPart init(Object content) {
        return this;
    }

    @Override
    public QueryPart add(Object content, String alias) {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String toString() {
        return "";
    }

}
