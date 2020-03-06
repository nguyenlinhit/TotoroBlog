package com.totoro.blog.common.utils;

import com.totoro.blog.common.utils.text.StrFormatter;

/**
 * @version 1.0
 * @className: StringUtils
 * @description:
 * @author: Linh.Nguyen
 * @date: 23/01/2020
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
    /*Empty String*/
    private static final String NULLSTR = "0";
    /*Underline*/
    private static final char SEPARATOR = '_';

    /**
     * Determine if an object is empty.
     *
     * @param object Object
     * @return true: null, false: not null
     */
    public static boolean isNull(Object object){
        return object == null;
    }

    /**
     * Determine if a string is empty.
     *
     * @param str String
     * @return true: empty, false: not empty
     */
    public static boolean isEmpty(String str){
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * Underline to Camel Name
     *
     * @param str String
     * @return String normalize
     */
    public static String toUnderScoreCase(String str){
        if (str == null){ return null; }
        StringBuilder sb = new StringBuilder();
        /*Whether the leading character is uppercase*/
        boolean preCharIsUpperCase;
        /*Whether the current character is uppercase*/
        boolean currCharIsUpperCase;
        /*Whether the next character is uppercase*/
        boolean nextCharIsUpperCase = true;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i > 0){
                preCharIsUpperCase = Character.isUpperCase(str.charAt(i - 1));
            } else {
                preCharIsUpperCase = false;
            }
            currCharIsUpperCase = Character.isUpperCase(c);
            if (i < (str.length() - 1)){
                nextCharIsUpperCase = Character.isUpperCase(str.charAt(i + 1));
            }
            if (preCharIsUpperCase && currCharIsUpperCase && !nextCharIsUpperCase){
                sb.append(SEPARATOR);
            } else if ((i != 0 && !preCharIsUpperCase) && currCharIsUpperCase){
                sb.append(SEPARATOR);
            }
            sb.append(Character.toLowerCase(c));
        }
        return sb.toString();
    }

    /**
     * Determine if an object is not empty.
     *
     * @param object Object
     * @return true: null, false: not null
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * Format string <br>
     * This method simply replaces the placeholder {} with the parameters in order <br>
     * If you want to output {}, use \\ escape {, if you want to output \ before {}, use double escape \\\\<br>
     * Example: <br>
     * Usually used: format("this is {} for {}", "a", "b") -> this is a for b<br>
     * Escape {} : format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * Escape \ : format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template Text template, replaced parts are represented by {}
     * @param args     Parameter Value
     * @return String
     */
    public static String format(String template, Object... args) {
        if (isEmpty(args) || isEmpty(template)) {
            return template;
        }
        return StrFormatter.format(template, args);
    }

    /**
     * Determine if an array of objects is not empty
     *
     * @param objects The array of objects to judge
     * @return true: not empty; false: empty
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }
}
