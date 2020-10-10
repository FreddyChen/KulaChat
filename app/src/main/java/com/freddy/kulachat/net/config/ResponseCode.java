package com.freddy.kulachat.net.config;

public enum ResponseCode {

    UNKNOWN(-1, "未知错误"),
    TIMEOUT(-2, "请求超时"),
    SUCCEED(0, "succeed"),
    FAILED(1, "failed"),
    PARAMS_IS_EMPTY(1001, "参数为空"),
    PHONE_IS_EMPTY(1002, "手机号为空"),
    ILLEGAL_PHONE(1003, "手机号不合法"),
    VERIFICATION_CODE_IS_EMPTY(1004, "验证码为空"),
    VERIFICATION_CODE_IS_INCORRECT(1005, "验证码不正确"),
    TOKEN_IS_EMPTY(2001, "token为空"),
    USER_DOES_NOT_EXIST(2002, "用户不存在"),
    TOKEN_EXPIRED(2003, "token已过期，请重新登录"),
    TOKEN_INVALID(2004, "token无效，请重新登录");

    private final int code;
    private final String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseCode codeOf(int code) {
        for (ResponseCode c : ResponseCode.values()) {
            if (c.getCode() == code) {
                return c;
            }
        }

        return UNKNOWN;
    }
}
