package com.home_projects.imarket.services.view;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
enum PartType {

    SELECT(SelectQueryPart.class, ", "),
    FROM(FromQueryPart.class, ""),
    JOIN(JoinQueryPart.class, " INNER JOIN "),
    WHERE(WhereQueryPart.class, " AND "),
    ORDER_BY(OrderByQueryPart.class, ", "),
    GROUP_BY(GroupByQueryPart.class, ", ");

    private Class<? extends QueryPart> type;
    private String delimiter;

    PartType(Class<? extends QueryPart> type, String delimiter) {
        this.type = type;
        this.delimiter = delimiter;
    }

}
