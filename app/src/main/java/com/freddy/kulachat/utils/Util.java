package com.freddy.kulachat.utils;

import java.util.regex.Pattern;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/28 01:09
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class Util {

    public static boolean isPhoneNumber(String phoneNumber) {
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        return p.matcher(phoneNumber).matches();
    }
}
