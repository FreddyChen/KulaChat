package com.freddy.kulachat;

import android.app.Application;

import com.freddy.kulachat.di.component.ApplicationComponent;
import com.freddy.kulachat.di.component.DaggerApplicationComponent;
import com.freddy.kulachat.listener.OnIMSConnectStatusListener;
import com.freddy.kulachat.utils.CrashHandler;
import com.freddy.kulaims.IMSKit;
import com.freddy.kulaims.config.CommunicationProtocol;
import com.freddy.kulaims.config.IMSOptions;
import com.freddy.kulaims.config.ImplementationMode;
import com.freddy.kulaims.config.TransportProtocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/21 17:27
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class KulaApp extends Application implements HasAndroidInjector {

    private static KulaApp instance;

    public static KulaApp getInstance() {
        return instance;
    }

    public ApplicationComponent applicationComponent;

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().build();
        applicationComponent.inject(this);
        instance = this;
        CrashHandler.getInstance().init();
        initIMS();
    }

    private void initIMS() {
        List<String> serverList = new ArrayList<>();
        serverList.add("192.168.0.93 8808");
        serverList.add("192.168.0.93 8809");
//        serverList.add("ws://192.168.0.93:8809/websocket");
//        serverList.add("ws://192.168.0.93:8810/websocket");
        IMSOptions options = new IMSOptions.Builder()
                .setImplementationMode(ImplementationMode.Netty)
                .setCommunicationProtocol(CommunicationProtocol.TCP)
                .setTransportProtocol(TransportProtocol.Protobuf)
                .setServerList(serverList)
                .build();
        IMSKit.getInstance().init(instance, options, new OnIMSConnectStatusListener(), null);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
