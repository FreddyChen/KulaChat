package com.freddy.kulachat.net.retrofit;

import com.freddy.kulachat.net.config.ResponseModel;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 21:43
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public interface IApiService {

    @GET
    Observable<ResponseModel> get(@Url String url);

    @GET
    Observable<ResponseModel> get(@Url String url, @QueryMap Map<String, Object> params);

    @Headers("Content-Type:application/json")
    @POST
    Observable<ResponseModel> post(@Url String url);

    @Headers("Content-Type:application/json")
    @POST
    Observable<ResponseModel> post(@Url String url, @Body RequestBody body);
}
