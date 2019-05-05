package com.home_projects.imarket.services.view.impl;

import com.home_projects.imarket.models.view.Field;
import com.home_projects.imarket.models.view.MainViewTable;
import com.home_projects.imarket.services.view.QueryPart;
import com.home_projects.imarket.services.view.SelectQueryPart;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SelectQueryPartImpl implements SelectQueryPart {

    private Map<String, Token> tokens;

    @Override
    public QueryPart init(Object content) {
        tokens = new HashMap<>();
        if (content == null) return this;
        if (content instanceof Boolean) {
            tokens.put(Strings.EMPTY, Token
                    .builder()
                    .content("count(*)")
                    .build());
        }
        List<Field> fields = (List<Field>) content;
        if (fields.isEmpty()) return this;
        for (Field field : fields) {
            MainViewTable mainTable = field.getMainTable();
            tokens.put(mainTable.getMainTableName(),
                    Token.builder()
                            .tableName(mainTable.getMainTableName())
                            .fieldName(field.getFieldName())
                            .build());
        }
        return this;
    }

    @Override
    public QueryPart add(Object content) {
        return null;
    }

}
