package com.freddy.kulachat.model;

import com.freddy.kulachat.net.RequestManagerFactory;
import com.freddy.kulachat.net.config.RequestMethod;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.listener.OnNetResponseListener;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:55
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class BaseModel {

    protected  <T> void request(String function, OnNetResponseListener<T> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setFunction(function)
                .build();
        this.request(options, listener);
    }

    protected <T> void request(String function, Map<String, Object> params, OnNetResponseListener<T> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setFunction(function)
                .setParams(params)
                .build();
        this.request(options, listener);
    }

    protected <T> void request(String baseUrl, String function, OnNetResponseListener<T> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setBaseUrl(baseUrl)
                .setFunction(function)
                .build();
        this.request(options, listener);
    }

    protected <T> void request(String baseUrl, String function, Map<String, Object> params, OnNetResponseListener<T> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setBaseUrl(baseUrl)
                .setFunction(function)
                .setParams(params)
                .build();
        this.request(options, listener);
    }

    protected <T> void request(String function, RequestMethod method, OnNetResponseListener<T> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setFunction(function)
                .setMethod(method)
                .build();
        this.request(options, listener);
    }

    protected <T> void request(String function, Map<String, Object> params, RequestMethod method, OnNetResponseListener<T> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setFunction(function)
                .setParams(params)
                .setMethod(method)
                .build();
        this.request(options, listener);
    }

    protected <T> void request(String baseUrl, String function, RequestMethod method, OnNetResponseListener<T> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setBaseUrl(baseUrl)
                .setFunction(function)
                .setMethod(method)
                .build();
        this.request(options, listener);
    }

    protected <T> void request(String baseUrl, String function, Map<String, Object> params, RequestMethod method, OnNetResponseListener<T> listener) {
        RequestOptions options = new RequestOptions.Builder()
                .setBaseUrl(baseUrl)
                .setFunction(function)
                .setParams(params)
                .setMethod(method)
                .build();
        this.request(options, listener);
    }

    protected <T> void request(RequestOptions options, OnNetResponseListener<T> listener) {
        RequestManagerFactory.getRequestManager().request(options, listener);
    }

    protected Observable<ResponseModel> request(String function) {
        RequestOptions options = new RequestOptions.Builder()
                .setFunction(function)
                .build();
        return this.request(options);
    }

    protected Observable<ResponseModel> request(String function, Map<String, Object> params) {
        RequestOptions options = new RequestOptions.Builder()
                .setFunction(function)
                .setParams(params)
                .build();
        return this.request(options);
    }

    protected Observable<ResponseModel> request(String baseUrl, String function) {
        RequestOptions options = new RequestOptions.Builder()
                .setBaseUrl(baseUrl)
                .setFunction(function)
                .build();
        return this.request(options);
    }

    protected Observable<ResponseModel> request(String baseUrl, String function, Map<String, Object> params) {
        RequestOptions options = new RequestOptions.Builder()
                .setBaseUrl(baseUrl)
                .setFunction(function)
                .setParams(params)
                .build();
        return this.request(options);
    }

    protected Observable<ResponseModel> request(String function, RequestMethod method) {
        RequestOptions options = new RequestOptions.Builder()
                .setFunction(function)
                .setMethod(method)
                .build();
        return this.request(options);
    }

    protected Observable<ResponseModel> request(String function, Map<String, Object> params, RequestMethod method) {
        RequestOptions options = new RequestOptions.Builder()
                .setFunction(function)
                .setParams(params)
                .setMethod(method)
                .build();
        return this.request(options);
    }

    protected Observable<ResponseModel> request(String baseUrl, String function, RequestMethod method) {
        RequestOptions options = new RequestOptions.Builder()
                .setBaseUrl(baseUrl)
                .setFunction(function)
                .setMethod(method)
                .build();
        return this.request(options);
    }

    protected Observable<ResponseModel> request(String baseUrl, String function, Map<String, Object> params, RequestMethod method) {
        RequestOptions options = new RequestOptions.Builder()
                .setBaseUrl(baseUrl)
                .setFunction(function)
                .setParams(params)
                .setMethod(method)
                .build();
        return this.request(options);
    }

    protected Observable<ResponseModel> request(RequestOptions options) {
        return RequestManagerFactory.getRequestManager().request(options);
    }
}
