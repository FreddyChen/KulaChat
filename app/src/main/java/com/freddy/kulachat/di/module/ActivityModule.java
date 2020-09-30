package com.freddy.kulachat.di.module;

import com.freddy.kulachat.di.component.ActivityComponent;
import com.freddy.kulachat.di.scope.ActivityScope;
import com.freddy.kulachat.view.main.SplashActivity;
import com.freddy.kulachat.view.user.LoginActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 00:45
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@Module(subcomponents = {ActivityComponent.class})
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = {SplashModule.class})
    abstract SplashActivity splashActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector()
    abstract LoginActivity loginActivityInjector();
}
