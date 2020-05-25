package com.freddy.kulachat.view.home;

import android.os.Bundle;
import android.util.Log;

import com.freddy.kulachat.R;
import com.freddy.kulachat.contract.HomeContract;
import com.freddy.kulachat.entity.TestModel;
import com.freddy.kulachat.entity.TestModel2;
import com.freddy.kulachat.net.config.RequestMethod;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.retrofit.CObserver;
import com.freddy.kulachat.net.retrofit.RetrofitRequestManager;
import com.freddy.kulachat.presenter.HomePresenter;
import com.freddy.kulachat.view.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    private static final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void start() {
        presenter.test();

        Map<String, String> headers = new HashMap<>();
        headers.put("token", "freddyc");
        RequestOptions requestOptions = new RequestOptions.Builder().setMethod(RequestMethod.POST).setFunction("wxarticle/list/408/1/json").build();
        Observable<ResponseModel> observable = RetrofitRequestManager.getInstance().request(requestOptions);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CObserver() {

            @Override
            protected void onCNext(ResponseModel responseModel) {
                TestModel testModel = parseObject(responseModel.getData(), TestModel.class);
                Log.d(TAG, "testModel = " + testModel);
            }
        });

        Map<String, String> headers2 = new HashMap<>();
        headers2.put("name", "hehe");
        RequestOptions requestOptions2 = new RequestOptions.Builder().setMethod(RequestMethod.POST).setFunction("wxarticle/chapters/json").build();
        Observable<ResponseModel> observable2 = RetrofitRequestManager.getInstance().request(requestOptions2);
        observable2.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new CObserver() {

            @Override
            protected void onCNext(ResponseModel responseModel) {
                List<TestModel2> testModel2List = parseArray(responseModel.getData(), TestModel2.class);
                Log.d(TAG, "testModel2List = " + testModel2List);
            }
        });
    }

    @Override
    public void onTestSuccess() {
        Log.d("HomeActivity", "onTestSuccess()");
    }
}
