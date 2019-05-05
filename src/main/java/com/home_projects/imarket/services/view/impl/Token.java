package com.home_projects.imarket.services.view.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class Token {

    private String tableName;

    private String fieldName;

    private String alias;

    private String content;

}
