package com.home_projects.imarket.services.view;

import com.home_projects.imarket.models.view.Field;
import com.home_projects.imarket.models.view.Filter;
import com.home_projects.imarket.models.view.JoinViewTable;
import com.home_projects.imarket.models.view.MainViewTable;
import com.home_projects.imarket.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class CustomQuery implements Query {

    private Map<PartType, QueryPart> parts = new EnumMap<>(PartType.class);

    public static Query init() {
        return new CustomQuery();
    }

    @Override
    public String toString() {
        return isEmpty() ? StringUtils.getEmpty() : parts.keySet()
                .stream()
                .map(k -> k.getTitle() + parts.get(k).toString())
                .collect(Collectors.joining(" "));
    }

    @Override
    public void clear() {
        if (parts != null) parts.clear();
    }

    @Override
    public void remove(QueryPartType type) {
        if (!parts.isEmpty()) parts.remove(PartType.valueOf(type.name()));
    }

    @Override
    public Query add(QueryPartType type, @Nullable String alias) {
        QueryPart part = parts.get(PartType.valueOf(type.name()));
        if (part != null) part.add(Collections.emptyList(), alias == null ? StringUtils.getEmpty() : alias);
        return this;
    }

    @Override
    public Query add(QueryPartType type, List<Field> fields, String alias) {
        return null;
    }

    @Override
    public Query select(boolean count) {
        if (count) parts.put(PartType.SELECT, PartType.SELECT.getEmpty().init(count));
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

    private boolean isEmpty() {
        return parts.isEmpty();
    }

}
