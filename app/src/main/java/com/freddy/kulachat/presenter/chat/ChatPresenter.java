package com.freddy.kulachat.presenter.chat;

import com.freddy.kulachat.contract.chat.ChatContract;
import com.freddy.kulachat.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 19:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class ChatPresenter extends BasePresenter<ChatContract.View> implements ChatContract.Presenter {

    @Inject
    public ChatPresenter() {

    }
}
