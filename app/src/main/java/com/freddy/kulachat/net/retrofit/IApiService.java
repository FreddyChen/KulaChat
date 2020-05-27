package com.freddy.kulachat.net.retrofit;

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
 * @date 2020/05/27 14:29
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public interface IApiService {

    @GET
    Observable<String> get(@Url String function);

    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @Headers("Content-Type:application/json")
    @POST
    Observable<String> post(@Url String url);

    @Headers("Content-Type:application/json")
    @POST
    Observable<String> post(@Url String url, @Body RequestBody body);
}
