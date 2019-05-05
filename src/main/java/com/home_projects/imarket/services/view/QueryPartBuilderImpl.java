package com.home_projects.imarket.services.view;

import org.springframework.stereotype.Component;

@Component
class QueryPartBuilderImpl implements QueryPartBuilder {

    @Override
    @SuppressWarnings("unchecked")
    public <T extends QueryPart> T build(PartType type) {
        try {
            Class<T> instanceType = (Class<T>) type.getType();
            return instanceType.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(CustomQuery.log("Cannot create query part: unknown type - " + type, false));
        }
    }

}
