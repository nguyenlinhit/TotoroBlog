package com.totoro.blog.common;

/**
 * @version 1.0
 * @className: SqlUtils
 * @description: SQL operation tools
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class SqlUtils {
    /*Only letters, numbers, underscores, spaces, commas are supported (multi-field sorting is supported)*/
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**
     *
     * @param value String
     * @return Value normalize
     */
    public static String escapeOrderBySql(String value){
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)){
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * Verify order by syntax
     *
     * @param value String
     * @return true: correct syntax, false: not correct syntax
     */
    private static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }
}
