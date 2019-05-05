package com.home_projects.imarket.util;

import org.apache.logging.log4j.util.Strings;

import java.util.UUID;

public class StringUtils {

    public static String getEmpty() {
        return Strings.EMPTY;
    }

    public static String getUUID() {
        return getEmpty() + UUID.randomUUID();
    }

}
