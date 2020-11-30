package com.freddy.kulachat.widget;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/09 14:01
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class BottomPaddingDecoration extends RecyclerView.ItemDecoration {

    private int padding;

    public BottomPaddingDecoration(int padding) {
        this.padding = padding;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int itemCount = layoutManager.getItemCount();
            int startPos, endPos;
            if (itemCount % spanCount == 0) {
                startPos = itemCount - spanCount;
            } else {
                startPos = itemCount - itemCount % spanCount;
            }
            endPos = itemCount;
            if (position >= startPos & position < endPos) {
                outRect.set(0, 0, 0, padding);
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            if (position == 0) {
                outRect.set(0, 0, 0, padding);
            }
        }
    }
}
