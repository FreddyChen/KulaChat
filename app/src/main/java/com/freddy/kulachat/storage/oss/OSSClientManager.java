package com.freddy.kulachat.storage.oss;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.freddy.kulachat.BuildConfig;
import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.net.config.NetworkConfig;
import com.freddy.kulachat.net.config.ResponseCode;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.retrofit.OkHttpManager;
import com.freddy.kulachat.net.retrofit.RetrofitWrapper;
import com.freddy.kulachat.utils.StringUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class OSSClientManager {

    private OSSClient client;

    private OSSClientManager() {

    }

    public static OSSClientManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final OSSClientManager INSTANCE = new OSSClientManager();
    }

    public void init() {
        Observable.create((ObservableOnSubscribe<OSSClient>) emitter -> {
            ClientConfiguration configuration = new ClientConfiguration();
            configuration.setConnectionTimeout(15 * 1000);// 连接超时，默认15秒
            configuration.setSocketTimeout(15 * 1000);// socket超时，默认15秒
            configuration.setMaxConcurrentRequest(5);// 最大并发请求数，默认5个
            configuration.setMaxErrorRetry(2);// 失败后最大重试次数，默认2次

            OSSCredentialProvider credentialProvider = new OSSFederationCredentialProvider() {

                @Override
                public OSSFederationToken getFederationToken() {
                    Log.d("OSSClientManager", "threadId = " + Thread.currentThread().getId());
                    final OkHttpClient.Builder builder = OkHttpManager.getInstance().getOkHttpClientBuilder();
                    OkHttpClient client = builder.build();
                    Request.Builder requestBuilder = new Request.Builder().url(BuildConfig.SERVER_URL + NetworkConfig.FUNC_CONFIG_GET_OSS_CREDENTIALS);
                    Map<String, String> headers = RetrofitWrapper.getHeaders();
                    for (Map.Entry<String, String> entry : headers.entrySet()) {
                        requestBuilder.addHeader(entry.getKey(), entry.getValue());
                    }

                    RequestBody requestBody = new RequestBody() {

                        @Nullable
                        @Override
                        public MediaType contentType() {
                            return MediaType.parse(NetworkConfig.CONTENT_TYPE);
                        }

                        @Override
                        public void writeTo(@NotNull BufferedSink bufferedSink) {

                        }
                    };

                    Request request = requestBuilder.post(requestBody).build();
                    try {
                        Response response = client.newCall(request).execute();
                        if (!response.isSuccessful()) return null;
                        String result = response.body().string();
                        if (StringUtil.isEmpty(request)) return null;
                        ResponseModel model = JSON.parseObject(result, ResponseModel.class);
                        if (model == null) return null;
                        int code = model.getCode();
                        if (code != ResponseCode.SUCCEED.getCode()) return null;
                        String data = model.getData();
                        if (StringUtil.isEmpty(data)) return null;
                        JSONObject jsonObj = JSON.parseObject(data);
                        if (jsonObj == null) return null;
                        String accessKeyId = jsonObj.getString(NetworkConfig.PARAM_CONFIG_OSS_ACCESS_KEY_ID);
                        String accessKeySecret = jsonObj.getString(NetworkConfig.PARAM_CONFIG_OSS_ACCESS_KEY_SECRET);
                        String securityToken = jsonObj.getString(NetworkConfig.PARAM_CONFIG_OSS_SECURITY_TOKEN);
                        String expiration = jsonObj.getString(NetworkConfig.PARAM_CONFIG_OSS_EXPIRATION);

                        return new OSSFederationToken(accessKeyId, accessKeySecret, securityToken, expiration);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            };

            emitter.onNext(new OSSClient(KulaApp.getInstance(), OSSConfig.ENDPOINT, credentialProvider, configuration));
            emitter.onComplete();
        }).subscribeOn(Schedulers.io()).subscribe(ossClient -> client = ossClient);
    }

    public OSSClient getOSSClient() {
        return client;
    }
}
