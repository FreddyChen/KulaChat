package com.freddy.kulachat.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.freddy.kulachat.ims.MsgContentType;
import com.freddy.kulachat.ims.MsgType;
import com.freddy.kulachat.utils.StringUtil;
import com.freddy.kulachat.view.chat.item.BaseChatItem;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/01 15:07
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class AppMessage implements MultiItemEntity {

    private String msgId;
    private String msgType;
    private String sender;
    private String receiver;
    private long timestamp;
    private int state;
    private String content;
    private String contentType;
    private String extend;

    public AppMessage(Builder builder) {
        this.msgId = builder.msgId;
        this.msgType = builder.msgType;
        this.sender = builder.sender;
        this.receiver = builder.receiver;
        this.timestamp = builder.timestamp;
        this.state = builder.state;
        this.content = builder.content;
        this.contentType = builder.contentType;
        this.extend = builder.extend;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getState() {
        return state;
    }

    public String getContent() {
        return content;
    }

    public String getContentType() {
        return contentType;
    }

    public String getExtend() {
        return extend;
    }

    @Override
    public int getItemType() {
        int itemType = BaseChatItem.ITEM_TYPE_UNKNOWN;
        String sender = this.sender;
        MsgType msgType = MsgType.typeOf(this.msgType);
        switch (msgType) {
            case SingleChat:
            case GroupChat:
                MsgContentType contentType = MsgContentType.typeOf(this.contentType);
                switch (contentType) {
                    case Text:
                        if(StringUtil.equals(sender, "1001")) {
                            itemType = BaseChatItem.ITEM_TYPE_TEXT_OUT;
                        }else {
                            itemType = BaseChatItem.ITEM_TYPE_TEXT_IN;
                        }
                        break;

                    case Picture:
                        break;

                    case Voice:
                        break;
                }
                break;
        }
        return itemType;
    }

    public static class Builder {

        private String msgId;
        private String msgType;
        private String sender;
        private String receiver;
        private long timestamp;
        private int state;
        private String content;
        private String contentType;
        private String extend;

        public Builder() {

        }

        public Builder setMsgId(String msgId) {
            this.msgId = msgId;
            return this;
        }

        public Builder setMsgType(String msgType) {
            this.msgType = msgType;
            return this;
        }

        public Builder setSender(String sender) {
            this.sender = sender;
            return this;
        }

        public Builder setReceiver(String receiver) {
            this.receiver = receiver;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setState(int state) {
            this.state = state;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setExtend(String extend) {
            this.extend = extend;
            return this;
        }

        public AppMessage build() {
            return new AppMessage(this);
        }
    }

    @Override
    public String toString() {
        return "AppMessage{" +
                "msgId='" + msgId + '\'' +
                ", msgType='" + msgType + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", timestamp=" + timestamp +
                ", state=" + state +
                ", content='" + content + '\'' +
                ", contentType='" + contentType + '\'' +
                ", extend='" + extend + '\'' +
                '}';
    }
}
