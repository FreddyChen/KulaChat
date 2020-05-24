package com.freddy.kulachat.di.component;

import android.content.Context;

import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.di.module.ApplicationModule;
import com.freddy.kulachat.di.module.ActivityModule;
import com.freddy.kulachat.di.module.ConfigModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:30
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
@Singleton
@Component(modules = {
        ApplicationModule.class,
        ActivityModule.class,
        ConfigModule.class,
        AndroidSupportInjectionModule.class
})
public interface ApplicationComponent extends AndroidInjector<KulaApp> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(KulaApp app);

        ApplicationComponent build();
    }

    Context getContext();
}
