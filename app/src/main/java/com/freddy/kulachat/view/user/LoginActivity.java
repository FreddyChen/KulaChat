package com.freddy.kulachat.view.user;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;

import com.freddy.kulachat.R;
import com.freddy.kulachat.config.AppConfig;
import com.freddy.kulachat.config.CConfig;
import com.freddy.kulachat.contract.user.LoginContract;
import com.freddy.kulachat.presenter.user.LoginPresenter;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.RxExecutorService;
import com.freddy.kulachat.utils.StringUtil;
import com.freddy.kulachat.utils.UIUtil;
import com.freddy.kulachat.utils.Util;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.home.HomeActivity;
import com.freddy.kulachat.widget.CTextButton;
import com.freddy.kulachat.widget.CTopBar;
import com.freddy.kulachat.widget.SoftKeyboardStateHelper;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 23:35
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.layout_main)
    ViewGroup mMainLayout;
    @BindView(R.id.c_top_bar)
    CTopBar mTopBar;
    @BindView(R.id.iv_logo)
    ImageView mLogoImageView;
    @BindView(R.id.layout_body)
    ViewGroup mBodyLayout;
    @BindView(R.id.btn_request_verifyCode)
    CTextButton mRequestVerifyCodeBtn;
    @BindView(R.id.et_phoneNumber)
    EditText mPhoneNumberEditText;
    @BindView(R.id.et_verifyCode)
    EditText mVerifyCodeEditText;
    @BindView(R.id.layout_verifyCode_wrap)
    ViewGroup mVerifyCodeWrapLayout;
    @BindView(R.id.btn_login)
    CTextButton mLoginBtn;
    private String phoneNumber;
    private String verifyCode;
    private SoftKeyboardStateHelper mSoftKeyboardStateHelper;
    private Disposable mDisposable;

    private boolean isAnimatorDisplayed = false;

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void init() {
        mTopBar.addMenu(R.drawable.ic_more_normal, R.drawable.ic_more_pressed, v -> {
            Toasty.normal(activity, "点中了菜单" + v.getId(), Toasty.LENGTH_SHORT).show();
        });
        mPhoneNumberEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(CConfig.MAX_PHONE_NUMBER_LENGTH)});
        mVerifyCodeEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(CConfig.MAX_VERIFY_CODE_LENGTH)});
        mSoftKeyboardStateHelper = new SoftKeyboardStateHelper(mMainLayout);

        mPhoneNumberEditText.postDelayed(() -> {
            UIUtil.requestFocus(mPhoneNumberEditText);
            UIUtil.showSoftInput(activity, mPhoneNumberEditText);
            startAnimator(true);
        }, 200);
    }

    @Override
    protected void setListeners() {
        mSoftKeyboardStateHelper.addSoftKeyboardStateListener(new SoftKeyboardStateHelper.SoftKeyboardStateListener() {

            @Override
            public void onSoftKeyboardOpened(int keyboardHeight) {
                AppConfig.saveKeyboardHeight(keyboardHeight);
                startAnimator(true);
            }

            @Override
            public void onSoftKeyboardClosed() {
                RxExecutorService.getInstance().delay(100, TimeUnit.MILLISECONDS, Schedulers.io(), AndroidSchedulers.mainThread(), new Observer() {


                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(Object o) {
                        if (mPhoneNumberEditText.isFocused() || mVerifyCodeEditText.isFocused()) {
                            return;
                        }
                        startAnimator(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        RxExecutorService.getInstance().dispose(mDisposable);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
            }
        });
    }

    private void startAnimator(boolean isShow) {
        if (isAnimatorDisplayed == isShow) {
            return;
        }
        isAnimatorDisplayed = isShow;
        float logoFromScaleXValue = isShow ? 1.0f : 0.65f;
        float logoToScaleYValue = isShow ? 0.65f : 1.0f;
        float logoFromTranslationYValue = isShow ? 0.0f : -DensityUtil.dp2px(64);
        float logoToTranslationYValue = isShow ? -DensityUtil.dp2px(64) : 0.0f;
        float bodyLayoutFromTranslationYValue = isShow ? 0.0f : -DensityUtil.dp2px(118);
        float bodyLayoutToTranslationYValue = isShow ? -DensityUtil.dp2px(118) : 0.0f;
        ObjectAnimator logoScaleXAnimator = ObjectAnimator.ofFloat(mLogoImageView, CConfig.ANIMATOR_SCALE_X, logoFromScaleXValue, logoToScaleYValue);
        ObjectAnimator logoScaleYAnimator = ObjectAnimator.ofFloat(mLogoImageView, CConfig.ANIMATOR_SCALE_Y, logoFromScaleXValue, logoToScaleYValue);
        ObjectAnimator logoTranslationYAnimator = ObjectAnimator.ofFloat(mLogoImageView, CConfig.ANIMATOR_TRANSLATION_Y, logoFromTranslationYValue, logoToTranslationYValue);
        ObjectAnimator bodyLayoutTranslationYAnimator = ObjectAnimator.ofFloat(mBodyLayout, CConfig.ANIMATOR_TRANSLATION_Y, bodyLayoutFromTranslationYValue, bodyLayoutToTranslationYValue);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(logoScaleXAnimator).with(logoScaleYAnimator).with(logoTranslationYAnimator).with(bodyLayoutTranslationYAnimator);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.setDuration(250);
        animatorSet.start();
    }

    @OnClick({R.id.btn_request_verifyCode, R.id.btn_login})
    void onClickListeners(View v) {
        switch (v.getId()) {
            case R.id.btn_request_verifyCode: {
                if (checkPhoneNumberInput()) {
                    Toasty.normal(this, "正在获取验证码", Toasty.LENGTH_SHORT).show();
                    showLoading();
                }
                break;
            }

            case R.id.btn_login: {
                startActivity(HomeActivity.class);
                finish();
                break;
            }
        }
    }

    private boolean checkPhoneNumberInput() {
        phoneNumber = mPhoneNumberEditText.getText().toString();
        if (StringUtil.isEmpty(phoneNumber)) {
            Toasty.warning(activity, "请输入手机号码", Toasty.LENGTH_SHORT).show();
            return false;
        }

        if (!Util.isPhoneNumber(phoneNumber)) {
            Toasty.warning(activity, "手机号码格式不正确", Toasty.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean checkVerifyCodeInput() {
        return true;
    }

    @Override
    protected void destroy() {
        if(mSoftKeyboardStateHelper != null) {
            mSoftKeyboardStateHelper.release();
        }
        RxExecutorService.getInstance().dispose(mDisposable);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (UIUtil.isShouldHideInput(v, ev)) {
                if (mPhoneNumberEditText.isFocused()) {
                    UIUtil.hideSoftInput(activity, mPhoneNumberEditText);
                    UIUtil.loseFocus(mPhoneNumberEditText);
                } else if (mVerifyCodeEditText.isFocused()) {
                    UIUtil.hideSoftInput(activity, mVerifyCodeEditText);
                    UIUtil.loseFocus(mVerifyCodeEditText);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    @Override
    protected boolean hasTransition() {
        return false;
    }
}
