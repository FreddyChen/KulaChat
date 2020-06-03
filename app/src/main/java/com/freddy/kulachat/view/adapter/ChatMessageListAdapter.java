package com.freddy.kulachat.view.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.AppMessage;
import com.freddy.kulachat.view.chat.item.BaseChatItem;
import com.freddy.kulachat.view.chat.item.ChatItemTextIn;
import com.freddy.kulachat.view.chat.item.ChatItemTextOut;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/01 15:23
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ChatMessageListAdapter extends BaseMultiItemQuickAdapter<AppMessage, BaseViewHolder> {

    public ChatMessageListAdapter(@Nullable List<AppMessage> data) {
        super(data);
        addItemType(BaseChatItem.ITEM_TYPE_UNKNOWN, R.layout.item_chat_unknown);
        addItemType(BaseChatItem.ITEM_TYPE_TEXT_IN, R.layout.item_chat_text_in);
        addItemType(BaseChatItem.ITEM_TYPE_TEXT_OUT, R.layout.item_chat_text_out);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, AppMessage appMessage) {
        BaseChatItem chatItem = null;
        switch (baseViewHolder.getItemViewType()) {
            case BaseChatItem.ITEM_TYPE_TEXT_IN:
                chatItem = new ChatItemTextIn();
                break;

            case BaseChatItem.ITEM_TYPE_TEXT_OUT:
                chatItem = new ChatItemTextOut();
                break;

            default:
//                chatItem = new ChatItemUnknown();
                break;
        }

        if (chatItem != null) {
            chatItem.bindView(baseViewHolder, appMessage);
        }
    }
}
