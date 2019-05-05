package com.home_projects.imarket.services.view;

public interface QueryPart {

    QueryPart init(Object content);

    QueryPart add(Object content, String alias);

}
