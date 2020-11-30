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

    public static final String CONTENT_TYPE = "application/json;charset=utf-8";
    public static final int CONNECT_TIMEOUT = 15 * 1000;
    public static final int READ_TIMEOUT = 15 * 1000;
    public static final int WRITE_TIMEOUT = 15 * 1000;

    public static final String FUNC_USER_GET_VERIFY_CODE = "user/getVerifyCode.action";
    public static final String FUNC_USER_LOGIN = "user/login.action";
    public static final String FUNC_USER_COMPLETE_INFO = "user/completeInfo.action";

    public static final String FUNC_CONFIG_GET_OSS_CREDENTIALS = "config/getOSSCredentials.action";

    public static final String PARAM_USER = "user";
    public static final String PARAM_USER_TOKEN = "token";
    public static final String PARAM_USER_ID = "userId";
    public static final String PARAM_USER_PHONE = "phone";
    public static final String PARAM_USER_VERIFY_CODE = "verifyCode";
    public static final String PARAM_USER_AVATAR = "avatar";
    public static final String PARAM_USER_NICKNAME = "nickname";
    public static final String PARAM_USER_GENDER = "gender";
    public static final String PARAM_USER_BIRTHDAY = "birthday";
    public static final String PARAM_USER_PROVINCE = "province";
    public static final String PARAM_USER_CITY = "city";
    public static final String PARAM_USER_AREA = "area";
    public static final String PARAM_USER_SIGNATURE = "signature";

    public static final String PARAM_CONFIG_OSS_ACCESS_KEY_ID = "accessKeyId";
    public static final String PARAM_CONFIG_OSS_ACCESS_KEY_SECRET = "accessKeySecret";
    public static final String PARAM_CONFIG_OSS_SECURITY_TOKEN = "securityToken";
    public static final String PARAM_CONFIG_OSS_EXPIRATION = "expiration";
}
