package com.freddy.kulachat.net.interf;

import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.listener.OnNetResponseListener;

import io.reactivex.Observable;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 22:32
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public interface IRequestInterface<Model> {

    void request(RequestOptions options, OnNetResponseListener listener);

    <T> void request(RequestOptions options, Class<T> cls, OnNetResponseListener listener);

    Observable<Model> request(RequestOptions options);
}
