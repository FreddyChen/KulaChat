package com.freddy.kulachat.view.chat.item;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.AppMessage;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/01 15:27
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ChatItemTextIn extends BaseChatItem {

    @Override
    public void bindView(BaseViewHolder viewHolder, AppMessage message) {
        viewHolder.setText(R.id.tv_content, message.getContent());
    }
}
