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

    public static Query init(MainViewTable mainViewTable) {
        Query query = null;
        if (mainViewTable != null){
            if (mainViewTable.getMainTableName() != null && !mainViewTable.getMainTableName().isEmpty()){
               if (mainViewTable.getFields() != null){
                   int invalidValues = 0;
                   for (Field field: mainViewTable.getFields() ) {
                       if (field.getFieldName() == null || field.getFieldName().isEmpty()) {
                           invalidValues++;
                           break;
                       }
                   }
                   if (invalidValues == 0) {
                       query = new CustomQuery();
                   }
               }
            }
        }
        return query;
    }

    @Override
    public String toString() {
        return isEmpty() ? StringUtils.getEmpty() : parts.keySet()
                .stream()
                .filter(k -> !parts.get(k).isEmpty())
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
    public Query add(QueryPartType type, @Nullable List<Field> fields, @Nullable String alias) {
        QueryPart part = parts.get(PartType.valueOf(type.name()));
        if (part != null) part.add(fields, alias == null ? StringUtils.getEmpty() : alias);
        return this;
    }

    @Override
    public Query select(boolean count) {
        if (count) parts.put(PartType.SELECT, PartType.SELECT.getEmpty().init(count));
        return this;
    }

    @Override
    public Query select(List<Field> fields) {
        if (isValid(fields)) {
            parts.put(PartType.SELECT, PartType.SELECT.getEmpty().init(fields));
            parts.put(PartType.WHERE, PartType.WHERE
                    .getEmpty()
                    .init(fields.stream()
                            .filter(field -> field.getFilter() != null)
                            .map(Field::getFilter)
                            .collect(Collectors.toList())));
        }
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
        if (parts.isEmpty()) return true;
        return parts.values().stream().allMatch(QueryPart::isEmpty);
    }

    private boolean isValid(List<Field> content) {
        if (content == null) return false;
        if (content.isEmpty()) return false;
        return content.stream().noneMatch(f -> f.getFieldName() == null || f.getFieldName().isEmpty());
    }

}
