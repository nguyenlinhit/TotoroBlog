package com.totoro.blog.common;

/**
 * @version 1.0
 * @className: ConvertUtils
 * @description: Type converter
 * @author: Linh.Nguyen
 * @date: 27/01/2020
 */
public class ConvertUtils {
    public ConvertUtils() {
    }

    /**
     * Convert to string <br>
     * If the given value is <code> null </code> or the conversion fails, the default value is <code> null </code> <br>
     * No error when conversion fails
     *
     * @param value Value
     * @param defaultValue Default Value
     * @return String
     */
    public static String toStr(Object value, String defaultValue){
        if (null == value) { return defaultValue; }
        if (value instanceof String) { return (String) value; }

        return value.toString();
    }

    /**
     * Convert to string <br>
     * If the given value is <code> null </code> or the conversion fails, the default value is <code> null </code> <br>
     * No error when conversion fails
     *
     * @param value Value
     * @return String
     */
    public static String toStr(Object value){
        return toStr(value, null);
    }

    /**
     * Convert to Int<br>
     * If the given value is <code> null </code> or the conversion fails, the default value is <code> null </code> <br>
     * No error when conversion fails.
     *
     * @param value Value
     * @param defaultValue Default Value
     * @return Integer
     */
    public static Integer toInt(Object value, Integer defaultValue){
        if (value == null){ return defaultValue; }
        if (value instanceof Integer) { return (Integer) value; }
        if (value instanceof Number) { return ((Number) value).intValue(); }
        final String valueStr = toStr(value, null);
        if (StringUtils.isEmpty(valueStr)) { return defaultValue; }
        try {
            return Integer.parseInt(valueStr.trim());
        } catch (Exception e){
            return defaultValue;
        }
    }

    /**
     * Convert to Int<br>
     * If the given value is <code> null </ code> or the conversion fails, the default value is <code> null </ code> <br>
     * No error when conversion fails.
     *
     * @param value Value
     * @return Integer
     */
    public static Integer toInt(Object value){
        return toInt(value, null);
    }
}
