package com.freddy.kulachat.di.component;

import com.freddy.kulachat.view.BaseActivity;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/26 02:56
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface ActivityComponent extends AndroidInjector<BaseActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<BaseActivity> { }
}
