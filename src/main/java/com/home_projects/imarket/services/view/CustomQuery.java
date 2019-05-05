package com.home_projects.imarket.services.view;

import com.home_projects.imarket.models.view.Field;
import com.home_projects.imarket.models.view.Filter;
import com.home_projects.imarket.models.view.JoinViewTable;
import com.home_projects.imarket.models.view.MainViewTable;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

@Slf4j
@NoArgsConstructor(staticName = "create")
public class CustomQuery implements Query {

    @Autowired
    private QueryPartBuilder queryPartBuilder;

    @Override
    public void clear() {

    }

    @Override
    public void remove(QueryPartType type) {

    }

    @Override
    public Query select(boolean count) {
        return null;
    }

    @Override
    public Query select(List<Field> fields) {
        return null;
    }

    @Override
    public Query from(MainViewTable mainTable) {
        return null;
    }

    @Override
    public Query join(List<JoinViewTable> joinTables) {
        return null;
    }

    @Override
    public Query where(List<Filter> filters) {
        return null;
    }

    @Override
    public Query orderBy(Sort sort) {
        return null;
    }

    @Override
    public Query groupBy(List<String> groups) {
        return null;
    }

    static String log(String message, boolean positive) {

        if (positive) log.debug(message);
        else log.error(message);
        return message;
    }

}
