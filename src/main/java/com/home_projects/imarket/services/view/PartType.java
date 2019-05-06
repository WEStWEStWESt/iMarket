package com.home_projects.imarket.services.view;

import com.home_projects.imarket.services.view.impl.*;
import lombok.Getter;

public enum PartType {

    SELECT("SELECT ", ", ") {
        @Override
        public QueryPart getEmpty() {
            return new SelectQueryPartImpl();
        }
    },
    FROM(" FROM ", "") {
        @Override
        public QueryPart getEmpty() {
            return new FromQueryPartImpl();
        }
    },
    JOIN(" INNER JOIN ", "") {
        @Override
        public QueryPart getEmpty() {
            return new JoinQueryPartImpl();
        }
    },
    WHERE(" WHERE ", " AND ") {
        @Override
        public QueryPart getEmpty() {
            return new WhereQueryPartImpl();
        }
    },
    ORDER_BY(" ORDER BY ", ", ") {
        @Override
        public QueryPart getEmpty() {
            return new OrderByQueryPartImpl();
        }
    },
    GROUP_BY(" GROUP BY ", ", ") {
        @Override
        public QueryPart getEmpty() {
            return new GroupByQueryPartImpl();
        }
    };

    @Getter
    private String title;

    @Getter
    private String delimiter;

    PartType(String title, String delimiter) {
        this.title = title;
        this.delimiter = delimiter;
    }

    PartType(String delimiter) {
        this.delimiter = delimiter;
    }

    public abstract QueryPart getEmpty();

}
