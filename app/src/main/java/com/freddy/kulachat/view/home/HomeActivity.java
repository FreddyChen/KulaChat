package com.freddy.kulachat.view.home;

import android.os.Bundle;
import android.util.Log;

import com.freddy.kulachat.R;
import com.freddy.kulachat.contract.HomeContract;
import com.freddy.kulachat.net.RequestManagerFactory;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.retrofit.CObserver;
import com.freddy.kulachat.presenter.HomePresenter;
import com.freddy.kulachat.view.BaseActivity;

import io.reactivex.Observable;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void start() {
        RequestOptions requestOptions = new RequestOptions.Builder()
                .setBaseUrl("https://wanandroid.com/")
                .setFunction("wxarticle/chapters/json")
                .build();

        Observable<ResponseModel> observable = RequestManagerFactory.getRequestManager().request(requestOptions);
        observable.subscribe(new CObserver() {
            @Override
            protected void onCNext(ResponseModel responseModel) {
                Log.d("HomeActivity", "responseModel = " + responseModel);
            }
        });
    }

    @Override
    public void onTestSuccess() {
        Log.d("HomeActivity", "onTestSuccess()");
    }
}
