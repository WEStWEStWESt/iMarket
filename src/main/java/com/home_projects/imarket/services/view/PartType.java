package com.home_projects.imarket.services.view;

import com.home_projects.imarket.services.view.impl.*;
import lombok.Getter;

public enum PartType {

    SELECT(", ") {
        @Override
        public QueryPart getEmpty() {
            return new SelectQueryPartImpl();
        }
    },
    FROM("") {
        @Override
        public QueryPart getEmpty() {
            return new FromQueryPartImpl();
        }
    },
    JOIN(" INNER JOIN ") {
        @Override
        public QueryPart getEmpty() {
            return new JoinQueryPartImpl();
        }
    },
    WHERE(" AND ") {
        @Override
        public QueryPart getEmpty() {
            return new WhereQueryPartImpl();
        }
    },
    ORDER_BY(", ") {
        @Override
        public QueryPart getEmpty() {
            return new OrderByQueryPartImpl();
        }
    },
    GROUP_BY(", ") {
        @Override
        public QueryPart getEmpty() {
            return new GroupByQueryPartImpl();
        }
    };

    @Getter
    private String delimiter;

    PartType(String delimiter) {
        this.delimiter = delimiter;
    }

    public abstract QueryPart getEmpty();

}
