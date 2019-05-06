package com.home_projects.imarket.services.view.impl;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Token implements Comparable<Token> {

    private String tableName;

    private String fieldName;

    private String alias;

    private int number;

    @Override
    public int compareTo(Token o) {
        return Integer.compare(this.number, o.number);
    }

}
