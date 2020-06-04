package com.freddy.kulachat.view.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.Expression;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/04 15:40
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ExpressionListAdapter extends BaseQuickAdapter<Expression, BaseViewHolder> {

    public ExpressionListAdapter(int layoutResId, @Nullable List<Expression> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder viewHolder, Expression expression) {
        viewHolder.setImageResource(R.id.iv_expression, expression.getResId());
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
