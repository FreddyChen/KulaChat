package com.freddy.kulachat.di.component;

import com.freddy.kulachat.di.scope.ActivityScope;
import com.freddy.kulachat.presenter.BasePresenter;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.IBaseView;

import dagger.Subcomponent;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 00:44
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@ActivityScope
@Subcomponent(modules = {AndroidInjectionModule.class})
public interface ActivityComponent extends AndroidInjector<BaseActivity<BasePresenter<IBaseView>>> {

    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BaseActivity<BasePresenter<IBaseView>>> {

    }
}
