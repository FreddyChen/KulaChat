package com.freddy.kulachat.widget;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.freddy.kulachat.R;
import com.freddy.kulachat.config.CConfig;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.StringUtil;
import com.freddy.kulachat.view.CActivityManager;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/28 16:28
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class CTopBar extends ConstraintLayout {

    public enum Mode {
        Normal,
        Loading
    }

    private Context mContext;
    private CImageButton mBackBtn;
    private LinearLayout mTitleWrapLayout;
    private TextView mTitleTextView;
    private ImageView mLoadingImageView;

    private int backgroundColor;
    private int backBtnNormalIcon;
    private int backBtnPressedIcon;
    private int backBtnVisibility;
    private String titleText;
    private int titleTextColor;
    private int titleTextSize;
    private ConstraintSet mConstraintSet;
    private static final int VISIBLE = 1;
    private static final int GONE = 0;
    private OnClickListener mOnBackBtnClickListener;

    public CTopBar(Context context) {
        this(context, null);
    }

    public CTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CTopBar, defStyleAttr, 0);
        backgroundColor = array.getColor(R.styleable.CTopBar_ctb_background_color, ContextCompat.getColor(context, R.color.c_app_main_color));
        backBtnNormalIcon = array.getResourceId(R.styleable.CTopBar_ctb_btn_back_normal_icon, R.drawable.ic_back_normal);
        backBtnPressedIcon = array.getResourceId(R.styleable.CTopBar_ctb_btn_back_pressed_icon, R.drawable.ic_back_pressed);
        backBtnVisibility = array.getInt(R.styleable.CTopBar_ctb_btn_back_visibility, VISIBLE);
        titleText = array.getString(R.styleable.CTopBar_ctb_title_text);
        titleTextColor = array.getColor(R.styleable.CTopBar_ctb_title_text_color, ContextCompat.getColor(context, R.color.c_000000));
        titleTextSize = array.getDimensionPixelSize(R.styleable.CTopBar_ctb_title_text_size, DensityUtil.sp2px(16));
        array.recycle();

        init();
    }

    private void init() {
        setId(R.id.c_top_bar);
        setBackgroundColor(backgroundColor);
        mConstraintSet = new ConstraintSet();
        mConstraintSet.clone(this);
        createTitleWrapLayout();
        createBackBtn();
        createTitleText();
        mConstraintSet.applyTo(this);
    }

    private void createBackBtn() {
        if (backBtnVisibility == GONE) {
            return;
        }

        mBackBtn = new CImageButton(mContext);
        mBackBtn.setId(R.id.ctb_btn_back);
        mBackBtn.setNormalImageResId(backBtnNormalIcon);
        mBackBtn.setPressedImageResId(backBtnPressedIcon);
        mBackBtn.setOnClickListener(v -> {
            if(mOnBackBtnClickListener != null) {
                mOnBackBtnClickListener.onClick(v);
            }else {
                CActivityManager.getInstance().finishActivity();
            }
        });

        addView(mBackBtn);

        mConstraintSet.constrainWidth(R.id.ctb_btn_back, DensityUtil.dp2px(54));
        mConstraintSet.constrainHeight(R.id.ctb_btn_back, DensityUtil.dp2px(36));
        mConstraintSet.centerVertically(R.id.ctb_btn_back, ConstraintSet.PARENT_ID);
    }

    private void createTitleWrapLayout() {
        mTitleWrapLayout = new LinearLayout(mContext);
        mTitleWrapLayout.setId(R.id.ctb_layout_title_wrap);
        mTitleWrapLayout.setOrientation(LinearLayout.HORIZONTAL);
        mTitleWrapLayout.setGravity(Gravity.CENTER_VERTICAL);

        addView(mTitleWrapLayout);

        mConstraintSet.constrainWidth(R.id.ctb_layout_title_wrap, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.constrainHeight(R.id.ctb_layout_title_wrap, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.centerVertically(R.id.ctb_layout_title_wrap, ConstraintSet.PARENT_ID);
        mConstraintSet.centerHorizontally(R.id.ctb_layout_title_wrap, ConstraintSet.PARENT_ID);
    }

    private void createTitleText() {
        if (StringUtil.isEmpty(titleText)) {
            return;
        }

        mTitleTextView = new TextView(mContext);
        mTitleTextView.setId(R.id.ctb_tv_title);
        mTitleTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        mTitleTextView.setText(titleText);
        mTitleTextView.setTextColor(titleTextColor);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
        mTitleTextView.getPaint().setFakeBoldText(true);

        mTitleWrapLayout.addView(mTitleTextView);
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
        if(mTitleTextView == null) {
            createTitleText();
        }

        mTitleTextView.setText(titleText);
    }

    private void createLoadingImageView() {
        mLoadingImageView = new ImageView(mContext);
        mLoadingImageView.setId(R.id.ctb_iv_loading);
        mLoadingImageView.setImageResource(R.drawable.ic_topbar_loading);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dp2px(32), DensityUtil.dp2px(32));
        lp.rightMargin = DensityUtil.dp2px(10);
        mTitleWrapLayout.addView(mLoadingImageView, 0, lp);
    }

    private SparseArray<View> mMenuArray;
    public void addMenu(int normalImgResId, int pressedImgResId, OnClickListener listener) {
        if (mMenuArray == null) {
            mMenuArray = new SparseArray<>();
        }

        int menuId = View.generateViewId();
        CImageButton menuBtn = new CImageButton(mContext);
        menuBtn.setId(menuId);
        menuBtn.setNormalImageResId(normalImgResId);
        menuBtn.setPressedImageResId(pressedImgResId);
        menuBtn.setOnClickListener(listener);
        addView(menuBtn);

        mConstraintSet.constrainWidth(menuId, DensityUtil.dp2px(42));
        mConstraintSet.constrainHeight(menuId, DensityUtil.dp2px(42));
        if (mMenuArray.size() == 0) {
            mConstraintSet.connect(menuId, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, DensityUtil.dp2px(8));
        } else {
            mConstraintSet.connect(menuId, ConstraintSet.RIGHT, mMenuArray.valueAt(mMenuArray.size() - 1).getId(), ConstraintSet.LEFT, DensityUtil.dp2px(8));
        }
        mConstraintSet.centerVertically(menuId, ConstraintSet.PARENT_ID);
        mConstraintSet.applyTo(this);
        mMenuArray.put(menuId, menuBtn);
    }

    public void addMenu(String text, OnClickListener listener) {
        if (mMenuArray == null) {
            mMenuArray = new SparseArray<>();
        }

        int menuId = View.generateViewId();
        CTextButton menuBtn = new CTextButton(mContext);
        menuBtn.setId(menuId);
        menuBtn.setText(text);
        menuBtn.setTextColor(ContextCompat.getColor(mContext, R.color.c_000000));
        menuBtn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14.0f);
        menuBtn.setPadding(DensityUtil.dp2px(6.0f), DensityUtil.dp2px(4.0f), DensityUtil.dp2px(6.0f), DensityUtil.dp2px(4.0f));
        menuBtn.setOnClickListener(listener);
        addView(menuBtn);

        mConstraintSet.constrainWidth(menuId, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.constrainHeight(menuId, ConstraintSet.WRAP_CONTENT);
        if (mMenuArray.size() == 0) {
            mConstraintSet.connect(menuId, ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, DensityUtil.dp2px(8));
        } else {
            mConstraintSet.connect(menuId, ConstraintSet.RIGHT, mMenuArray.valueAt(mMenuArray.size() - 1).getId(), ConstraintSet.LEFT, DensityUtil.dp2px(8));
        }
        mConstraintSet.centerVertically(menuId, ConstraintSet.PARENT_ID);
        mConstraintSet.applyTo(this);
        mMenuArray.put(menuId, menuBtn);
    }

    public void setOnBackBtnClickListener(OnClickListener listener) {
        this.mOnBackBtnClickListener = listener;
    }

    public void setMode(Mode mode) {
        Log.d("CTopBar", "setMode() mode = " + mode);
        switch (mode) {
            case Normal:
                if(mLoadingImageView != null && mLoadingImageView.getVisibility() == View.VISIBLE) {
                    mLoadingImageView.setVisibility(View.GONE);
                    stopLoadingImageViewAnim();
                }
                break;

            case Loading:
                if(mLoadingImageView == null) {
                    createLoadingImageView();
                }
                mLoadingImageView.setVisibility(View.VISIBLE);
                startLoadingImageViewAnim();
                break;
        }
    }

    private ObjectAnimator mLoadingImageViewAnimator;
    private void startLoadingImageViewAnim() {
        if(mLoadingImageView == null) {
            return;
        }

        stopLoadingImageViewAnim();

        ObjectAnimator.ofPropertyValuesHolder(mLoadingImageView, PropertyValuesHolder.ofFloat(CConfig.ANIMATOR_ROTATION, 0.0f, 360.0f));
        mLoadingImageViewAnimator = ObjectAnimator.ofFloat(mLoadingImageView, CConfig.ANIMATOR_ROTATION, 0.0f, 360.0f);
        mLoadingImageViewAnimator.setRepeatMode(ValueAnimator.RESTART);
        mLoadingImageViewAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mLoadingImageViewAnimator.setDuration(1000);
        mLoadingImageViewAnimator.setInterpolator(new LinearInterpolator());
        mLoadingImageViewAnimator.start();
    }

    private void stopLoadingImageViewAnim() {
        if(mLoadingImageView == null) {
            return;
        }

        if(mLoadingImageViewAnimator != null && mLoadingImageViewAnimator.isRunning()) {
            mLoadingImageViewAnimator.cancel();
        }
        mLoadingImageViewAnimator = null;
    }
}
