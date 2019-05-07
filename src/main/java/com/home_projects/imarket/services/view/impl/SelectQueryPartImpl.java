package com.home_projects.imarket.services.view.impl;

import com.home_projects.imarket.models.view.Field;
import com.home_projects.imarket.models.view.MainViewTable;
import com.home_projects.imarket.services.view.CustomQuery;
import com.home_projects.imarket.services.view.PartType;
import com.home_projects.imarket.services.view.QueryPart;
import com.home_projects.imarket.services.view.SelectQueryPart;
import com.home_projects.imarket.util.APIConstants;
import com.home_projects.imarket.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SelectQueryPartImpl implements SelectQueryPart {

    private Map<String, SelectToken> tokens;

    @Override
    public QueryPart init(Object content) {
        tokens = new HashMap<>();
        if (content == null) return this;
        if (content instanceof Boolean) {
            if (Boolean.TRUE.equals(content)) {
                tokens.put(StringUtils.getEmpty(), SelectToken.of(Boolean.TRUE));
            }
        } else {
            List<Field> fields = (List<Field>) content;
            if (fields.isEmpty()) return this;
            for (Field field : fields) {
                String tableName = field.isJoined() ? field.getJoinTable().getJoinTableName() : field.getMainTable().getMainTableName();
                tokens.put(String.valueOf(field.getFieldNumber()),
                        SelectToken.builder()
                                .tableName(tableName)
                                .fieldName(field.getFieldName())
                                .number(field.getFieldNumber())
                                .build());
            }
        }
        return this;
    }

    @Override
    public QueryPart add(Object content, String alias) {
        String MESSAGE_PREFIX = "Cannot edit SELECT part: ";
        if (tokens != null) {
            if (!alias.isEmpty()) {
                List<Field> fields = (List<Field>) content;
                if (!fields.isEmpty()) {
                    for (Field field : fields) {
                        String tableName =  field.isJoined() ? field.getJoinTable().getJoinTableName() : field.getMainTable().getMainTableName();
                        tokens.put(tableName, SelectToken.builder()
                                .fieldName(field.getFieldName())
                                .number(field.getFieldNumber())
                                .tableName(tableName)
                                .alias(alias)
                                .build());
                    }
                } tokens.values().forEach(f -> f.setAlias(alias));
            } else CustomQuery.log(MESSAGE_PREFIX + APIConstants.VIEW_SELECT_ALIAS_IS_EMPTY, true);
        } else CustomQuery.log(MESSAGE_PREFIX + APIConstants.VIEW_SELECT_CONTENT_IS_EMPTY, true);
        return this;
    }

    @Override
    public boolean isEmpty() {
        return tokens == null || tokens.isEmpty();
    }

    @Override
    public String toString() {
        return isEmpty() ? StringUtils.getEmpty() : tokens.values()
                .stream()
                .sorted()
                .map(Token::toString)
                .collect(Collectors.joining(PartType.SELECT.getDelimiter()));
    }

}
