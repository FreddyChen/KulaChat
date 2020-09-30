package com.freddy.kulachat.ims.config;

import com.freddy.kulachat.utils.StringUtil;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/01 18:00
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public enum MsgContentType {

    Text("text"),
    Picture("picture"),
    Voice("voice"),
    Unknown("unknown");

    String type;

    MsgContentType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static MsgContentType typeOf(String type) {
        for (MsgContentType contentType : MsgContentType.values()) {
            if (StringUtil.equals(contentType.getType(), type)) {
                return contentType;
            }
        }

        return Unknown;
    }
}
