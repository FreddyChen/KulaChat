package com.freddy.kulachat.view.home;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import com.freddy.kulachat.R;
import com.freddy.kulachat.contract.home.HomeContract;
import com.freddy.kulachat.net.RequestManagerFactory;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.retrofit.CObserver;
import com.freddy.kulachat.presenter.home.HomePresenter;
import com.freddy.kulachat.view.BaseActivity;

import es.dmoral.toasty.Toasty;
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

    private long firstClickTime = 0L;
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondClickTime = System.currentTimeMillis();
            if(secondClickTime - firstClickTime > 2000) {
                Toasty.warning(activity, "再按一次退出程序", Toasty.LENGTH_SHORT).show();
                firstClickTime = secondClickTime;
                return true;
            }else {
                finish();
            }
        }

        return super.onKeyUp(keyCode, event);
    }
}
