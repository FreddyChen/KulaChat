package com.freddy.kulachat.view.chat;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.freddy.kulachat.R;
import com.freddy.kulachat.config.CConfig;
import com.freddy.kulachat.contract.chat.ChatContract;
import com.freddy.kulachat.presenter.chat.ChatPresenter;
import com.freddy.kulachat.utils.UIUtil;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.widget.SoftKeyboardStateHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 19:49
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public abstract class BaseChatActivity extends BaseActivity<ChatPresenter> implements ChatContract.View {

    @BindView(R.id.layout_main)
    ViewGroup mMainLayout;
    @BindView(R.id.recycler_view)
    ChatRecyclerView mRecyclerView;
    @BindView(R.id.chat_input_panel)
    ChatInputPanel mInputPanel;
    protected MessageListAdapter mMessageListAdapter;
    protected List<String> mMessageList;
    private SoftKeyboardStateHelper mSoftKeyboardStateHelper;

    public static void startSingleChat(Context context) {
        Intent intent = new Intent(context, SingleChatActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_chat);
    }

    @Override
    protected void init() {
        mMessageList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mMessageList.add("消息" + (i + 1));
        }
        mMessageListAdapter = new MessageListAdapter(R.layout.item_conversation, mMessageList);
        mRecyclerView.setAdapter(mMessageListAdapter);
        mRecyclerView.scrollToBottom();
        mSoftKeyboardStateHelper = new SoftKeyboardStateHelper(mMainLayout);
//        mInputPanel.setSoftKeyboardStateHelper(mSoftKeyboardStateHelper);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setListeners() {
        mSoftKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {

            @Override
            public void onSoftKeyboardOpened(int keyboardHeightInPx) {
                mRecyclerView.scrollToBottom();
            }

            @Override
            public void onSoftKeyboardClosed() {
                UIUtil.loseFocus(mInputPanel.getEditText());
            }
        });
        mRecyclerView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                mInputPanel.hide();
            }
            return false;
        });
        mRecyclerView.setOnLoadMoreListener(new ChatRecyclerView.OnLoadMoreListener() {

            @Override
            public void onShowLoading() {
                showHideLoadMore(true);
            }

            @Override
            public void onHideLoading() {
                showHideLoadMore(false);
            }

            @Override
            public void onLoadMore() {
                onLoadMoreMessage();
            }
        });
    }

    private ChatLoadMoreView mLoadMoreView;

    private void showHideLoadMore(boolean isShow) {
        if (isShow) {
            if (mLoadMoreView == null) {
                mLoadMoreView = new ChatLoadMoreView(activity);
            }
            mMessageListAdapter.addHeaderView(mLoadMoreView);
            mLoadMoreView.show();
        } else {
            if (mLoadMoreView != null) {
                mMessageListAdapter.removeHeaderView(mLoadMoreView);
                mLoadMoreView.hide();
            }
        }
    }

    protected abstract void onLoadMoreMessage();

    static class MessageListAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MessageListAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(R.id.tv_name, s);
        }
    }
}
