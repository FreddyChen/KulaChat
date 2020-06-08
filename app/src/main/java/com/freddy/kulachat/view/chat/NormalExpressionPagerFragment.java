package com.freddy.kulachat.view.chat;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.Expression;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.view.BaseFragment;
import com.freddy.kulachat.view.adapter.ExpressionListAdapter;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/04 15:37
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class NormalExpressionPagerFragment extends BaseFragment<NullablePresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private static final String KEY_EXPRESSION_LIST = "key_expression_list";
    private List<Expression> mExpressionList;

    public static NormalExpressionPagerFragment newInstance(List<Expression> expressionList) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_EXPRESSION_LIST, (Serializable) expressionList);
        NormalExpressionPagerFragment fragment = new NormalExpressionPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setRootView(Bundle saveInstanceState) {
        setLayout(R.layout.fragment_normal_expression_pager);
    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        mExpressionList = (List<Expression>) bundle.getSerializable(KEY_EXPRESSION_LIST);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 7);
        mRecyclerView.setLayoutManager(layoutManager);
        ExpressionListAdapter adapter = new ExpressionListAdapter(R.layout.item_expression, mExpressionList);
        mRecyclerView.setAdapter(adapter);
    }

    private int currentVisiblePercent;

    @Override
    protected void setListeners() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (recyclerView.getLayoutManager() == null || !(recyclerView.getLayoutManager() instanceof GridLayoutManager))
                    return;
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                int spanCount = layoutManager.getSpanCount();
                int lastPosition = layoutManager.findLastVisibleItemPosition();

                if ((lastPosition + 1) % spanCount == 0) {
                    FrameLayout lastFirstLayout = (FrameLayout) layoutManager.findViewByPosition(lastPosition);
                    lastFirstLayout.setAlpha(0.0f);
                    FrameLayout lastSecondLayout = (FrameLayout) layoutManager.findViewByPosition(lastPosition - 1);
                    lastSecondLayout.setAlpha(0.0f);
                    FrameLayout lastThirdLayout = (FrameLayout) layoutManager.findViewByPosition(lastPosition - spanCount);
                    lastThirdLayout.setAlpha(0.0f);
                    FrameLayout lastFourthLayout = (FrameLayout) layoutManager.findViewByPosition(lastPosition - spanCount - 1);
                    lastFourthLayout.setAlpha(0.0f);
                }

                Rect rvRect = new Rect();
                //获取recyclerview可见区域相对屏幕左上角的位置坐标
                recyclerView.getGlobalVisibleRect(rvRect);
                int visiblePercent;
                //根据position获得对应的view
                View itemView = layoutManager.findViewByPosition(lastPosition);
                int itemHeight = itemView.getHeight();
                Rect rowRect = new Rect();
                //获取item可见区域相对屏幕左上角的位置坐标
                itemView.getGlobalVisibleRect(rowRect);
                if (rowRect.bottom >= rvRect.bottom) { //item在recyclerview底部且有部分不可见
                    int visibleHeightFirst = rvRect.bottom - rowRect.top;
                    visiblePercent = (visibleHeightFirst * 100) / itemHeight;
                } else { //item在recyclerview中或顶部
                    int visibleHeightFirst = rowRect.bottom - rvRect.top;
                    visiblePercent = (visibleHeightFirst * 100) / itemHeight;
                }
                if (visiblePercent >= 100) {
                    visiblePercent = 100;
                }
                if (visiblePercent <= 0) {
                    visiblePercent = 0;
                }
                if (currentVisiblePercent == visiblePercent) {
                    return;
                }
                currentVisiblePercent = visiblePercent;
                float alpha = visiblePercent / 100.0f;
                Log.d("NormalExpressionPagerFragment", "lastPosition = " + lastPosition + "\tvisiblePercent = " + visiblePercent + "\talpha = " + alpha);
                if ((lastPosition + 1) % spanCount == 0) {
                    FrameLayout lastFirstLayout = (FrameLayout) layoutManager.findViewByPosition(lastPosition - spanCount * 2);
                    lastFirstLayout.setAlpha(alpha);
                    FrameLayout lastSecondLayout = (FrameLayout) layoutManager.findViewByPosition(lastPosition - spanCount * 2 - 1);
                    lastSecondLayout.setAlpha(alpha);
                } else {
                    if (lastPosition == itemCount - 1) {
                        FrameLayout lastFirstLayout = (FrameLayout) layoutManager.findViewByPosition(itemCount - (itemCount % spanCount) - spanCount - 1);
                        lastFirstLayout.setAlpha(alpha);
                        FrameLayout lastSecondLayout = (FrameLayout) layoutManager.findViewByPosition(itemCount - (itemCount % spanCount) - spanCount - 2);
                        lastSecondLayout.setAlpha(alpha);
                        FrameLayout lastThirdLayout = (FrameLayout) layoutManager.findViewByPosition(itemCount - (itemCount % spanCount) - 1);
                        lastThirdLayout.setAlpha(alpha);
                        FrameLayout lastFourthLayout = (FrameLayout) layoutManager.findViewByPosition(itemCount - (itemCount % spanCount) - 2);
                        lastFourthLayout.setAlpha(alpha);
                    }
                }
            }
        });
    }
}