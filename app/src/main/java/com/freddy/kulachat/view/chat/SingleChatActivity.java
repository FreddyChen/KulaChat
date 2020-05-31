package com.freddy.kulachat.view.chat;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 19:56
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class SingleChatActivity extends BaseChatActivity {

    @Override
    protected void onLoadMoreMessage() {
        Toasty.success(activity, "加载更多单聊消息", Toasty.LENGTH_SHORT).show();
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            list.add(0, "加载更多的消息" + (i + 1));
        }
        mMessageList.addAll(0, list);
        mMessageListAdapter.notifyItemRangeInserted(0, list.size());
    }
}
