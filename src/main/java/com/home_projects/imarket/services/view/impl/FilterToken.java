package com.home_projects.imarket.services.view.impl;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FilterToken extends Token {

    private String content;

    @Builder
    public FilterToken(String tableName, String fieldName, String alias, int number, String content) {
        super(tableName, fieldName, alias, number);
        this.content = content;
    }

}
