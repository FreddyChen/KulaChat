package com.freddy.kulachat.di.component;

import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.di.module.ApplicationModule;
import com.freddy.kulachat.di.module.ActivityModule;
import com.freddy.kulachat.net.retrofit.DingDingRetrofitRequestManager;
import com.freddy.kulachat.net.retrofit.RetrofitRequestManager;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 00:47
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        ActivityModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        ApplicationComponent build();
    }

    void inject(KulaApp app);
    RetrofitRequestManager getRetrofitRequestManager();
    DingDingRetrofitRequestManager getDingDingRetrofitRequestManager();
}
