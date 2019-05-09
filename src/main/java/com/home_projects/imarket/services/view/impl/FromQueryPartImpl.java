package com.home_projects.imarket.services.view.impl;

import com.home_projects.imarket.services.view.FromQueryPart;
import com.home_projects.imarket.services.view.QueryPart;
import org.springframework.stereotype.Component;

@Component
public class FromQueryPartImpl implements FromQueryPart {

    @Override
    public QueryPart init(Object content) {
        return null;
    }

    @Override
    public QueryPart add(Object content, String alias) {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

}
