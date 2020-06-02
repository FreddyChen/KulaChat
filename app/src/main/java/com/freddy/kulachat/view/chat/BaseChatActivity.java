package com.freddy.kulachat.view.chat;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.freddy.kulachat.R;
import com.freddy.kulachat.config.CConfig;
import com.freddy.kulachat.contract.chat.ChatContract;
import com.freddy.kulachat.entity.AppMessage;
import com.freddy.kulachat.ims.MsgContentType;
import com.freddy.kulachat.ims.MsgType;
import com.freddy.kulachat.presenter.chat.ChatPresenter;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.widget.SoftKeyboardStateHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 19:49
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public abstract class BaseChatActivity extends BaseActivity<ChatPresenter> implements ChatContract.View {

    @BindView(R.id.layout_body)
    ViewGroup mBodyLayout;
    @BindView(R.id.recycler_view)
    ChatRecyclerView mRecyclerView;
    @BindView(R.id.chat_input_panel)
    ChatInputPanel mInputPanel;
    protected ChatMessageListAdapter mMessageListAdapter;
    protected List<AppMessage> mChatMessageList;

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
        mChatMessageList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            AppMessage.Builder messageBuilder = new AppMessage.Builder()
                    .setMsgId(UUID.randomUUID().toString())
                    .setMsgType(MsgType.SingleChat.getType())
                    .setTimestamp(System.currentTimeMillis())
                    .setState(0)
                    .setContent("消息" + (i + 1))
                    .setContentType(MsgContentType.Text.getType());

            if ((i & 1) == 1) {
                messageBuilder.setSender("1001").setReceiver("1002");
            } else {
                messageBuilder.setSender("1002").setReceiver("1001");
            }

            mChatMessageList.add(messageBuilder.build());
        }
        mMessageListAdapter = new ChatMessageListAdapter(mChatMessageList);
        mRecyclerView.setAdapter(mMessageListAdapter);
        mRecyclerView.scrollToBottom();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setListeners() {
        mRecyclerView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                mInputPanel.reset();
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
        mInputPanel.setOnLayoutAnimatorHandleListener(this::handleBodyLayoutMoveAnimator);
    }

    private void handleBodyLayoutMoveAnimator(float fromValue, float toValue) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mBodyLayout, CConfig.ANIMATOR_TRANSLATION_Y, fromValue, toValue);
        animator.setDuration(200);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
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

    @Override
    protected void destroy() {
        //        mInputPanel.release();
    }
}
