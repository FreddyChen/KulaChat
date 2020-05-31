package com.freddy.kulachat.view.chat;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.Conversation;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.view.BaseFragment;
import com.freddy.kulachat.view.adapter.ConversationListAdapter;
import com.freddy.kulachat.widget.ReboundRecyclerView;

import java.util.ArrayList;
import java.util.List;

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
        mConversationListAdapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                BaseChatActivity.startSingleChat(getActivity());
            }
        });
    }
}
