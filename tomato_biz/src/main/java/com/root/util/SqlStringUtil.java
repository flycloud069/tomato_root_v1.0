package com.root.util;

import cn.hutool.core.util.StrUtil;

public class SqlStringUtil {
    public static String escapeString(String input) {
        if (StrUtil.isEmpty(input)){
            return input;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            switch (c) {
                case '"':
                    sb.append("\\\"");
                    break;
                case '\'':
                    sb.append("\\\'");
                    break;
                case '\n':
                    sb.append("\\n");
                    break;
                case '\t':
                    sb.append("\\t");
                    break;
                case '\r':
                    sb.append("\\r");
                    break;
                case '\\':
                    sb.append("\\\\");
                    break;
                default:
                    if (Character.isISOControl(c)) {
                        sb.append("\\").append((int) c).append(' ');
                    } else {
                        sb.append(c);
                    }
                    break;
            }
        }

        return sb.toString();
    }

}
