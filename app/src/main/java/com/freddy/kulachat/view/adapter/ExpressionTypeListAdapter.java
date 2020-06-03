package com.freddy.kulachat.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.ExpressionType;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/03 20:14
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ExpressionTypeListAdapter extends BaseQuickAdapter<ExpressionType, BaseViewHolder> {

    public ExpressionTypeListAdapter(int layoutResId, @Nullable List<ExpressionType> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder viewHolder, ExpressionType expressionType) {
        viewHolder.setImageResource(R.id.iv_expression_icon, expressionType.getTypeResId());
    }
}
