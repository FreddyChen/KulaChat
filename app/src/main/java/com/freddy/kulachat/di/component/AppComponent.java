package com.freddy.kulachat.di.component;

import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.di.module.ActivityModule;
import com.freddy.kulachat.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/26 01:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@Singleton
@Component(modules = {
        AppModule.class,
        ActivityModule.class,
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class
})
public interface AppComponent extends AndroidInjector<KulaApp> {

    @Component.Builder
    interface Builder {
        AppComponent build();
    }

    void inject(KulaApp application);
}
