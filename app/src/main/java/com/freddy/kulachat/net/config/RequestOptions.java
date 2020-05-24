package com.freddy.kulachat.net.config;

import com.freddy.kulachat.BuildConfig;

import java.util.Map;

import retrofit2.http.POST;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 22:32
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class RequestOptions {

    private String baseUrl;
    private String function;
    private RequestMethod method;
    private Map<String, Object> params;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public RequestMethod getMethod() {
        return method;
    }

    public void setMethod(RequestMethod method) {
        this.method = method;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public static class Builder {

        private RequestOptions options;

        public Builder() {
            options = new RequestOptions();
            options.setBaseUrl(BuildConfig.SERVER_URL);
        }

        public Builder setBaseUrl(String baseUrl) {
            options.setBaseUrl(baseUrl);
            return this;
        }

        public Builder setFunction(String function) {
            options.setFunction(function);
            return this;
        }

        public Builder setMethod(RequestMethod method) {
            options.setMethod(method);
            return this;
        }

        public Builder setParams(Map<String, Object> params) {
            options.setParams(params);
            return this;
        }

        public RequestOptions build() {
            return options;
        }
    }
}
