package com.freddy.kulachat.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.Conversation;

import org.jetbrains.annotations.NotNull;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 16:51
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class ConversationListAdapter extends BaseQuickAdapter<Conversation, BaseViewHolder> {

    public ConversationListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Conversation conversation) {
        baseViewHolder.setText(R.id.tv_name, conversation.getContent());
    }
}
