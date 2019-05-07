package com.home_projects.imarket.services.view.impl;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class SelectToken extends Token {

    private static final String ASTERISK = "*";
    private static final String COUNT_VALUE = "count(*)";

    private boolean count;

    @Builder
    public SelectToken(String tableName, String fieldName, String alias, int number, boolean count) {
        super(tableName, fieldName, alias, number);
        this.count = count;
    }

    @Override
    public String toString() {
        String result;
        String alias = getAlias();
        if (count) result = COUNT_VALUE.replaceAll("\\*", alias != null ? alias : ASTERISK);
        else result = alias == null || alias.isEmpty() ? getFieldName() : alias + "." + getFieldName();
        return result;
    }

}
