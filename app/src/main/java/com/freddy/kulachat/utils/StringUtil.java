package com.freddy.kulachat.utils;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 22:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class StringUtil {

    public static boolean isEmpty(Object o) {
        return null == o || o.toString().trim().equals("");
    }

    public static boolean isNotEmpty(Object o) {
        return !StringUtil.isEmpty(o);
    }

    public static boolean equals(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equals(str2);
    }
}
