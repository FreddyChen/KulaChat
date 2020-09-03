package com.freddy.kulachat.view.chat;

import android.os.Bundle;

import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.Conversation;
import com.freddy.kulachat.event.Events;
import com.freddy.kulachat.event.obj.IMSConnectStatusEventObj;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.view.BaseFragment;
import com.freddy.kulachat.view.adapter.ConversationListAdapter;
import com.freddy.kulachat.widget.CTopBar;
import com.freddy.kulachat.widget.ReboundRecyclerView;
import com.freddy.kulaims.config.IMSConnectStatus;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 02:08
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class MessageFragment extends BaseFragment<NullablePresenter> {

    @BindView(R.id.c_top_bar)
    CTopBar mTopBar;
    @BindView(R.id.recycler_view_wrap)
    ReboundRecyclerView mRecyclerViewWrap;
    private List<Conversation> mConversationList;
    private ConversationListAdapter mConversationListAdapter;

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setRootView(Bundle saveInstanceState) {
        setLayout(R.layout.fragment_message);
    }

    @Override
    protected void init() {
        mConversationList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Conversation conversation = new Conversation();
            conversation.setContent("会话" + (i + 1));
            mConversationList.add(conversation);
        }


        mRecyclerViewWrap.setScrollRatio(0.45f);
        RecyclerView recyclerView = mRecyclerViewWrap.getRecyclerView();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mConversationListAdapter = new ConversationListAdapter(R.layout.item_conversation);
        recyclerView.setAdapter(mConversationListAdapter);
        mConversationListAdapter.setNewInstance(mConversationList);
    }

    @Override
    protected void setListeners() {
        mConversationListAdapter.setOnItemClickListener((adapter, view, position) -> BaseChatActivity.startSingleChat(getActivity()));
    }

    @Override
    protected String[] getEvents() {
        return new String[]{
                Events.EVENT_IMS_CONNECT_STATUS
        };
    }

    private void updateIMSConnectStatus(IMSConnectStatus status) {
        String text = null;
        switch (status) {
            case Unconnected:
                text = "未连接";
                mTopBar.setMode(CTopBar.Mode.Normal);
                break;

            case Connecting:
                text = "连接中";
                mTopBar.setMode(CTopBar.Mode.Loading);
                break;

            case Connected:
                text = "消息";
                mTopBar.setMode(CTopBar.Mode.Normal);
                break;

            case ConnectFailed:
                text = "连接失败";
                mTopBar.setMode(CTopBar.Mode.Normal);
                break;
        }

        mTopBar.setTitleText(text);
    }

    @Override
    public void onCEvent(String topic, int msgCode, int resultCode, Object obj) {
        switch (topic) {
            case Events.EVENT_IMS_CONNECT_STATUS:
                IMSConnectStatusEventObj eventObj = (IMSConnectStatusEventObj) obj;
                updateIMSConnectStatus(eventObj.getImsConnectStatus());
                break;

            default:
                break;
        }
    }
}
