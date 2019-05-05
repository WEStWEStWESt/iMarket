package com.home_projects.imarket.services.view;

import com.home_projects.imarket.models.view.Field;
import com.home_projects.imarket.models.view.Filter;
import com.home_projects.imarket.models.view.JoinViewTable;
import com.home_projects.imarket.models.view.MainViewTable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface Query {

    void clear();

    void remove(QueryPartType type);

    Query select(boolean count);

    Query select(List<Field> fields);

    Query from(MainViewTable mainTable);

    Query join(List<JoinViewTable> joinTables);

    Query where(List<Filter> filters);

    Query orderBy(Sort sort);

    Query groupBy(List<String> groups);

}
