package com.freddy.kulachat.ims.config;

import com.freddy.kulachat.utils.StringUtil;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/01 16:33
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public enum MsgType {

    SingleChat("single_chat"),
    GroupChat("group_chat"),
    Unknown("unknown");

    String type;

    MsgType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static MsgType typeOf(String type) {
        for(MsgType msgType : MsgType.values()) {
            if(StringUtil.equals(msgType.getType(), type)) {
                return msgType;
            }
        }

        return Unknown;
    }
}
