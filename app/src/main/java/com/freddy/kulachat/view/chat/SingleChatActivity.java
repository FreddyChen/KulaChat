package com.freddy.kulachat.view.chat;

import com.freddy.kulachat.entity.AppMessage;
import com.freddy.kulachat.ims.MsgContentType;
import com.freddy.kulachat.ims.MsgType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 19:56
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class SingleChatActivity extends BaseChatActivity {

    private int currentIndex = 0;

    @Override
    protected void onLoadMoreMessage() {
        List<AppMessage> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            currentIndex++;
            AppMessage.Builder messageBuilder = new AppMessage.Builder()
                    .setMsgId(UUID.randomUUID().toString())
                    .setMsgType(MsgType.SingleChat.getType())
                    .setTimestamp(System.currentTimeMillis())
                    .setState(0)
                    .setContent("加载更多的消息" + currentIndex)
                    .setContentType(MsgContentType.Text.getType());
            if ((i & 1) == 1) {
                messageBuilder.setSender("1002").setReceiver("1001");
            } else {
                messageBuilder.setSender("1001").setReceiver("1002");
            }
            list.add(0, messageBuilder.build());
        }
        mChatMessageList.addAll(0, list);
        mMessageListAdapter.notifyItemRangeInserted(0, list.size());
    }
}
