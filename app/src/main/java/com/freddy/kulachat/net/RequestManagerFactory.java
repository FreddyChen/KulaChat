package com.freddy.kulachat.net;

import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.net.config.ServerType;
import com.freddy.kulachat.net.interf.IRequestInterface;
import com.freddy.kulachat.net.retrofit.DingDingRetrofitRequestManager;
import com.freddy.kulachat.net.retrofit.RetrofitRequestManager;

import javax.inject.Inject;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 15:13
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class RequestManagerFactory {

    private RequestManagerFactory() {}

    public static IRequestInterface getRequestManager() {
        return RequestManagerFactory.getRequestManager(ServerType.INNER);
    }

    public static IRequestInterface getRequestManager(ServerType serverType) {
        switch (serverType) {
            case INNER:
            default: {
                return KulaApp.getInstance().applicationComponent.getRetrofitRequestManager();
            }

            case DINGDING: {
                return KulaApp.getInstance().applicationComponent.getDingDingRetrofitRequestManager();
            }
        }
    }
}
