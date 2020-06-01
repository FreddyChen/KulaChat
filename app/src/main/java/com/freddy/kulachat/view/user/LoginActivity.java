package com.freddy.kulachat.view.user;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.freddy.kulachat.R;
import com.freddy.kulachat.config.AppConfig;
import com.freddy.kulachat.config.CConfig;
import com.freddy.kulachat.contract.user.LoginContract;
import com.freddy.kulachat.presenter.user.LoginPresenter;
import com.freddy.kulachat.utils.StringUtil;
import com.freddy.kulachat.utils.UIUtil;
import com.freddy.kulachat.utils.Util;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.home.HomeActivity;
import com.freddy.kulachat.widget.CTextButton;
import com.freddy.kulachat.widget.CTopBar;
import com.freddy.kulachat.widget.SoftKeyboardStateHelper;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 23:35
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {

    @BindView(R.id.c_top_bar)
    CTopBar mTopBar;
    @BindView(R.id.btn_request_verifyCode)
    CTextButton mRequestVerifyCodeBtn;
    @BindView(R.id.et_phoneNumber)
    EditText mPhoneNumberEditText;
    @BindView(R.id.et_verifyCode)
    EditText mVerifyCodeEditText;
    private String phoneNumber;
    private String verifyCode;

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
        UIUtil.requestFocus(mPhoneNumberEditText);
    }

    @OnClick({R.id.btn_request_verifyCode, R.id.btn_login})
    void onClickListeners(View v) {
        switch (v.getId()) {
            case R.id.btn_request_verifyCode : {
                if(checkPhoneNumberInput()) {
                    Toasty.normal(this, "正在获取验证码", Toasty.LENGTH_SHORT).show();
                }
                showLoading();
                break;
            }

            case R.id.btn_login : {
                startActivity(HomeActivity.class);
                finish();
                break;
            }
        }
    }

    private boolean checkPhoneNumberInput() {
        phoneNumber = mPhoneNumberEditText.getText().toString();
        if(StringUtil.isEmpty(phoneNumber)) {
            Toasty.warning(activity, "请输入手机号码", Toasty.LENGTH_SHORT).show();
            return false;
        }

        if(!Util.isPhoneNumber(phoneNumber)) {
            Toasty.warning(activity, "手机号码格式不正确", Toasty.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private boolean checkVerifyCodeInput() {
        return true;
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
}
