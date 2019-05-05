package com.home_projects.imarket.services.view.util;

import com.home_projects.imarket.util.StringUtils;

public class AliasUtil {

    public static String create(String tableName) {
        return create(tableName, StringUtils.getEmpty());
    }

    public static String create(String tableName, String oldAlias) {
        String result = StringUtils.getEmpty();
        char[] chars = tableName.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char current = tableName.charAt(i);
            if (i == 0) result = result + Character.toLowerCase(current);
            if (i > 0 && Character.isUpperCase(current)) result = result + Character.toLowerCase(current);
        }
        return !oldAlias.isEmpty() ? oldAlias + result : result;
    }

}
