package com.freddy.kulachat.net.interf;

import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ResponseModel;

import io.reactivex.Observable;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 22:32
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public interface IRequestInterface {

    Observable<ResponseModel> request(RequestOptions options);
}
