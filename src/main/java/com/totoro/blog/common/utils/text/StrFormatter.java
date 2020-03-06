package com.totoro.blog.common.utils.text;

import com.totoro.blog.common.utils.ConvertUtils;
import com.totoro.blog.common.utils.StringUtils;

/**
 * @version 1.0
 * @className: StrFormatter
 * @description: String formatting
 * @author: LinhNguyen
 * @date: 2/29/2020
 */
public class StrFormatter {
    public static final String EMPTY_JSON = "{}";
    public static final char C_BACKSLASH = '\\';
    public static final char C_DELIMIT_START = '{';
    public static final char C_DELIMIT_END = '}';

    /**
     * Format string <br>
     * This method simply replaces the placeholder {} with the parameters in order <br>
     * If you want to output {}, use \\ escape {, if you want to output \ before {}, use double escape \\\\<br>
     * Example: <br>
     * Usually used: format("this is {} for {}", "a", "b") -> this is a for b<br>
     * Escape {} : format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * Escape \ : format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param strPatten String Template
     * @param args      Parameter List
     * @return String
     */
    public static String format(final String strPatten, final Object... args) {
        if (StringUtils.isEmpty(strPatten) || StringUtils.isEmpty(args)) {
            return strPatten;
        }

        final int strPattenLength = strPatten.length();
        StringBuilder strBuilder = new StringBuilder(strPattenLength + 50);
        int handlePosition = 0;
        int delimitIndex;

        for (int argIndex = 0; argIndex < args.length; argIndex++) {
            delimitIndex = strPatten.indexOf(EMPTY_JSON, handlePosition);
            if (delimitIndex == -1) {
                if (handlePosition == 0) {
                    return strPatten;
                } else {
                    strBuilder.append(strPatten, handlePosition, strPattenLength);
                    return strBuilder.toString();
                }
            } else {
                if (delimitIndex > 0 && strPatten.charAt(delimitIndex - 1) == C_BACKSLASH) {
                    if (delimitIndex > 1 && strBuilder.charAt(delimitIndex - 2) == C_BACKSLASH) {
                        strBuilder.append(strBuilder, handlePosition, delimitIndex - 1);
                        strBuilder.append(ConvertUtils.utf8Str(args[argIndex]));
                        handlePosition += delimitIndex + 1;
                    } else {
                        argIndex--;
                        strBuilder.append(strPatten, handlePosition, delimitIndex - 1);
                        strBuilder.append(C_DELIMIT_START);
                        handlePosition = delimitIndex + 1;
                    }
                } else {
                    strBuilder.append(strPatten, handlePosition, delimitIndex);
                    strBuilder.append(ConvertUtils.utf8Str(args[argIndex]));
                    handlePosition = delimitIndex + 2;
                }
            }
        }
        strBuilder.append(strPatten, handlePosition, strPatten.length());

        return strBuilder.toString();
    }
}
