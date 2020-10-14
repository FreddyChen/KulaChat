package com.freddy.kulachat.view.user;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.freddy.kulachat.R;
import com.freddy.kulachat.contract.user.CompleteInfoContract;
import com.freddy.kulachat.entity.User;
import com.freddy.kulachat.manager.DelayManager;
import com.freddy.kulachat.presenter.user.CompleteInfoPresenter;
import com.freddy.kulachat.utils.DateUtil;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.StringUtil;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.CActivityManager;
import com.freddy.kulachat.view.home.HomeActivity;
import com.freddy.kulachat.widget.CTextButton;
import com.freddy.kulachat.widget.CTopBar;
import com.wheelpicker.AdministrativeMap;
import com.wheelpicker.AdministrativeUtil;
import com.wheelpicker.DataPicker;
import com.wheelpicker.OnCascadeWheelListener;
import com.wheelpicker.PickOption;
import com.wheelpicker.widget.AbstractViewWheelPicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class CompleteInfoActivity extends BaseActivity<CompleteInfoPresenter> implements CompleteInfoContract.View {

    @BindView(R.id.c_top_bar)
    CTopBar mTopBar;
    @BindView(R.id.iv_avatar)
    ImageView mAvatarImageView;
    @BindView(R.id.et_nickname)
    EditText mNicknameEditText;
    @BindView(R.id.tv_gender)
    CTextButton mGenderTextView;
    @BindView(R.id.tv_birthday)
    CTextButton mBirthdayTextView;
    @BindView(R.id.tv_area)
    CTextButton mAreaTextView;
    @BindView(R.id.et_signature)
    EditText mSignatureEditText;
    private List<String> genderList = new ArrayList<>();
    private Date birthdayDate = new Date();
    private AdministrativeMap administrativeMap;
    private List<Integer> cascadeInitIndexList = null;

    private String avatar;
    private String nickname;
    private int gender;
    private String birthday;
    private String province;
    private String city;
    private String area;
    private String signature;

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_complete_info);
        getLifecycle().addObserver(DelayManager.getInstance());
    }

    @Override
    protected void init() {
        genderList.add("男");
        genderList.add("女");
        birthdayDate.setTime(631123200000L);// 从1990-01-01开始
        administrativeMap = AdministrativeUtil.loadCity(getApplicationContext());

        mTopBar.addMenu("提交", v -> {
            completeInfo();
        });

        Glide.with(this).load(Uri.parse("content://media/external/images/media/1175")).into(mAvatarImageView);
    }

    @OnClick({R.id.tv_gender, R.id.tv_birthday, R.id.tv_area})
    void onClickListeners(View v) {
        switch (v.getId()) {
            case R.id.tv_gender: {
                showGenderSelectBottomSheet();
                break;
            }

            case R.id.tv_birthday: {
                showBirthdaySelectBottomSheet();
                break;
            }

            case R.id.tv_area: {
                showAreaSelectBottomSheet();
                break;
            }
        }
    }

    private void showGenderSelectBottomSheet() {
        DataPicker.pickData(activity, null, genderList, buildPickOption("请选择性别"), (index, val, data) -> {
            mGenderTextView.setText(val);
        });
    }


    private void showBirthdaySelectBottomSheet() {
        DataPicker.pickBirthday(activity, birthdayDate, buildPickOption("请选择生日"), dateTimePicker -> {
            birthdayDate.setTime(dateTimePicker.getTime());
            mBirthdayTextView.setText(DateUtil.formatDate(birthdayDate.getTime(), DateUtil.TIME_YYYY_MM_DD));
        });
    }

    private void showAreaSelectBottomSheet() {
        DataPicker.pickData(activity, cascadeInitIndexList, AdministrativeUtil.getPickData(administrativeMap, cascadeInitIndexList), buildPickOption("请选择地区"), false,
                (indexArr, val, data) -> {
                    StringBuilder result = new StringBuilder();
                    for(int i = 0; i < val.size(); i++) {
                        result.append(val.get(i)).append("-");
                    }
                    result.deleteCharAt(result.length() - 1);
                    mAreaTextView.setText(result.toString());
                    cascadeInitIndexList = indexArr;
                },
                (OnCascadeWheelListener<List<?>>) (wheelIndex, itemIndex) -> {
                    if (wheelIndex == 0) {
                        return administrativeMap.provinces.get(itemIndex.get(0)).city;
                    } else if (wheelIndex == 1) {
                        return administrativeMap.provinces.get(itemIndex.get(0)).city.get(itemIndex.get(1)).areas;
                    }

                    return null;
                });
    }

    private PickOption buildPickOption(String title) {
        return new PickOption.Builder()
                .setVisibleItemCount(9) //设置pickerView有多少个可见的item，必须是单数（1，3，5，7....）
                .setItemSpace(DensityUtil.dp2px(10.0f)) //设置item的间距
                .setItemTextColor(ContextCompat.getColor(getApplicationContext(), R.color.c_000000)) //设置item的文本颜色
                .setItemTextSize(DensityUtil.sp2px(18.0f)) //设置item的字体大小
                .setVerPadding(DensityUtil.dp2px(8.0f)) //设置item的顶部，底部的padding
                .setShadowGravity(AbstractViewWheelPicker.SHADOW_RIGHT) //设置滚动的偏向
                .setShadowFactor(0.5f) //设置滚轮的偏向因子
                .setFingerMoveFactor(0.8f) //设置手指滑动的阻尼因子
                .setFlingAnimFactor(0.7f) //设置手指快速放开后，滚动动画的阻尼因子
                .setOverScrollOffset(DensityUtil.dp2px(20.0f)) //设置滚轮滑动到底端顶端回滚动画的最大偏移
                .setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.c_ffffff)) //设置滚轮的背景颜色
                .setLeftTitleColor(ContextCompat.getColor(getApplicationContext(), R.color.c_999999)) //设置底部弹出框左边文本的颜色
                .setRightTitleColor(ContextCompat.getColor(getApplicationContext(), R.color.c_999999)) //设置底部弹出框右边文本的颜色
                .setMiddleTitleColor(ContextCompat.getColor(getApplicationContext(), R.color.c_333333)) //设置底部弹出框中间文本的颜色
                .setTitleBackground(ContextCompat.getColor(getApplicationContext(), R.color.c_ffffff)) //设置底部弹框title栏的背景颜色
                .setLeftTitleText("取消") //设置底部弹出框左边文本
                .setRightTitleText("确定") //设置底部弹出框右边文本
                .setMiddleTitleText(title) //设置底部弹出框中间
                .setTitleHeight(DensityUtil.dp2px(56.0f)) //设置底部弹框title高度
                .build();
    }

    private void completeInfo() {
        if (checkInputData()) {
            presenter.completeInfo(avatar, nickname, gender, birthday, province, city, area, signature);
        }
    }

    private boolean checkInputData() {
        nickname = mNicknameEditText.getText().toString();
        if (StringUtil.isEmpty(nickname)) {
            Toasty.warning(activity, "请输入昵称").show();
            return false;
        }

        String gender = mGenderTextView.getText().toString();
        if (StringUtil.isEmpty(gender)) {
            Toasty.warning(activity, "请选择性别").show();
            return false;
        }

        this.gender = StringUtil.equals(gender, "男") ? User.GENDER_MALE : User.GENDER_FEMALE;

        birthday = mBirthdayTextView.getText().toString();

        String address = mAreaTextView.getText().toString();
        if (StringUtil.isNotEmpty(address)) {
            String[] addressSplit = address.split("-");
            province = addressSplit[0];
            city = addressSplit[1];
            area = addressSplit[2];
        }

        signature = mSignatureEditText.getText().toString();

        return true;
    }

    @Override
    public void showCompleteInfoLoadingDialog() {
        showLoadingDialog("正在提交信息", false, false);
    }

    @Override
    public void onCompleteInfoSucceed() {
        startActivity(HomeActivity.class);
        DelayManager.getInstance().startDelay(500, TimeUnit.MILLISECONDS, () -> {
            CActivityManager.getInstance().finishActivity(CompleteInfoActivity.class);
        });
    }
}
