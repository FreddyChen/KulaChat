package com.freddy.kulachat.view.chat.item;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.freddy.kulachat.entity.AppMessage;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/01 15:06
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public abstract class BaseChatItem {

    public static final int ITEM_TYPE_UNKNOWN = 0;
    public static final int ITEM_TYPE_TEXT_IN = 101;
    public static final int ITEM_TYPE_TEXT_OUT = 102;

    public abstract void bindView(BaseViewHolder viewHolder, AppMessage message);
}
