package com.freddy.kulachat.di.module;

import com.freddy.kulachat.di.scope.ActivityScope;
import com.freddy.kulachat.view.home.HomeActivity;
import com.freddy.kulachat.view.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:39
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
@Module
public abstract class BuilderModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivityInject();

    @ActivityScope
    @ContributesAndroidInjector
    abstract HomeActivity homeActivityInject();
}
