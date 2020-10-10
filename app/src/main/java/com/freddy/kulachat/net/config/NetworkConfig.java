package com.freddy.kulachat.net.config;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 22:38
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class NetworkConfig {

    public static final int REQUEST_TIMEOUT = 15 * 1000;
    public static final int READ_TIMEOUT = 15 * 1000;
    public static final int WRITE_TIMEOUT = 15 * 1000;

    public static final String FUNC_USER_GET_VERIFY_CODE = "user/getVerifyCode.action";
    public static final String FUNC_USER_LOGIN = "user/login.action";

    public static final String PARAM_USER = "user";
    public static final String PARAM_USER_TOKEN = "token";
    public static final String PARAM_USER_ID = "userId";
    public static final String PARAM_USER_PHONE = "phone";
    public static final String PARAM_USER_VERIFY_CODE = "verifyCode";
}
