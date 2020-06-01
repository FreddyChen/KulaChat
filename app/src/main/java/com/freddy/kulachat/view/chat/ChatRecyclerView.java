package com.freddy.kulachat.view.chat;

import android.content.Context;
import android.util.AttributeSet;

import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.widget.RecyclerViewSpacesItemDecoration;

import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 21:50
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class ChatRecyclerView extends RecyclerView {

    private Context mContext;
    private boolean canLoadMore = true;
    private boolean isLoading = false;

    public ChatRecyclerView(@NonNull Context context) {
        this(context, null);
    }

    public ChatRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        setLayoutManager(layoutManager);
        Map<String, Integer> decorationMap = new HashMap<>(1);
        decorationMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION, DensityUtil.dp2px(mContext, 8));
        addItemDecoration(new RecyclerViewSpacesItemDecoration(decorationMap));

        addOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (canLoadMore && !isLoading) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
                    int lastVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                    // 第二条可见就触发加载
                    if (lastVisibleItemPosition <= 2) {
                        isLoading = true;
                        if (mOnLoadMoreListener != null) {
                            post(() -> mOnLoadMoreListener.onShowLoading());
                        }

                        postDelayed(() -> {
                            if (mOnLoadMoreListener != null) {
                                mOnLoadMoreListener.onLoadMore();
                                isLoading = false;
                                mOnLoadMoreListener.onHideLoading();
                            }
                        }, 1000);
                    }
                }
            }
        });
    }

    public void scrollToBottom(boolean delay) {
        if (delay) {
            postDelayed(mScrollToBottomDelayRunnable, 200);
        } else {
            scrollToPosition(getAdapter().getItemCount() - 1);
        }
    }

    private Runnable mScrollToBottomDelayRunnable = new Runnable() {

        @Override
        public void run() {
            scrollToPosition(getAdapter().getItemCount() - 1);
        }
    };

    public void setCanLoadMore(boolean canLoadMore) {
        this.canLoadMore = canLoadMore;
    }

    private OnLoadMoreListener mOnLoadMoreListener;

    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.mOnLoadMoreListener = listener;
    }

    public interface OnLoadMoreListener {
        void onShowLoading();

        void onHideLoading();

        void onLoadMore();
    }
}
