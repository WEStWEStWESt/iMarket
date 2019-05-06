package com.home_projects.imarket.services.view;

import com.home_projects.imarket.models.view.Field;
import com.home_projects.imarket.models.view.Filter;
import com.home_projects.imarket.models.view.JoinViewTable;
import com.home_projects.imarket.models.view.MainViewTable;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@NoArgsConstructor(staticName = "init")
public class CustomQuery implements Query {

    private Map<PartType, QueryPart> parts = new EnumMap<>(PartType.class);

    @Override
    public void clear() {
        if (parts != null) parts.clear();
    }

    @Override
    public void remove(QueryPartType type) {
        if (parts != null) parts.remove(PartType.valueOf(type.name()));
    }

    @Override
    public Query select(boolean count) {
        parts.put(PartType.SELECT, PartType.SELECT.getEmpty().init(count ? Boolean.TRUE : null));
        return this;
    }

    @Override
    public Query select(List<Field> fields) {
        parts.put(PartType.SELECT, PartType.SELECT.getEmpty().init(fields));
        parts.put(PartType.WHERE, PartType.WHERE
                .getEmpty()
                .init(fields.stream()
                        .map(Field::getFilter)
                        .collect(Collectors.toList())));
        return this;
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

    public static String log(String message, boolean info) {
        if (info) log.info(message);
        else log.debug(message);
        return message;
    }

}
