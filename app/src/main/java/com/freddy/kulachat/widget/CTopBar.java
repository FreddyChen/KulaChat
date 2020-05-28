package com.freddy.kulachat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.widget.TextView;

import com.freddy.kulachat.R;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.StringUtil;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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

    private Context mContext;
    private CImageButton mBackBtn;
    private TextView mTitleTextView;

    private int backgroundColor;
    private int backBtnNormalIcon;
    private int backBtnPressedIcon;
    private int backBtnVisibility;
    private String titleText;
    private int titleTextColor;
    private int titleTextSize;

    private ConstraintSet mConstraintSet;

    private static final int VISIBLE = 0;
    private static final int GONE = 1;
    private static final int[] VISIBILITY_FLAG = {VISIBLE, GONE};

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
        if (array != null) {
            backgroundColor = array.getColor(R.styleable.CTopBar_ctb_background_color, ContextCompat.getColor(context, R.color.c_app_main_color));
            backBtnNormalIcon = array.getResourceId(R.styleable.CTopBar_ctb_btn_back_normal_icon, R.drawable.ic_back_normal);
            backBtnPressedIcon = array.getResourceId(R.styleable.CTopBar_ctb_btn_back_pressed_icon, R.drawable.ic_back_pressed);
            backBtnVisibility = VISIBILITY_FLAG[array.getInt(R.styleable.CTopBar_ctb_btn_back_visibility, VISIBLE)];
            titleText = array.getString(R.styleable.CTopBar_ctb_title_text);
            titleTextColor = array.getColor(R.styleable.CTopBar_ctb_title_text_color, ContextCompat.getColor(context, R.color.c_000000));
            titleTextSize = array.getDimensionPixelSize(R.styleable.CTopBar_ctb_title_text_size, DensityUtil.sp2px(14));
            array.recycle();
        }

        init();
    }

    private void init() {
        setId(R.id.c_top_bar);
        setBackgroundColor(backgroundColor);
        setFitsSystemWindows(true);

        mConstraintSet = new ConstraintSet();
        mConstraintSet.clone(this);
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

        addView(mBackBtn);

        mConstraintSet.constrainWidth(R.id.ctb_btn_back, DensityUtil.dp2px(72));
        mConstraintSet.constrainHeight(R.id.ctb_btn_back, DensityUtil.dp2px(48));
        mConstraintSet.centerVertically(R.id.ctb_btn_back, ConstraintSet.PARENT_ID);
    }

    private void createTitleText() {
        if(StringUtil.isEmpty(titleText)) {
            return;
        }

        mTitleTextView = new TextView(mContext);
        mTitleTextView.setId(R.id.ctb_tv_title);
        mTitleTextView.setFilters(new InputFilter[]{new InputFilter.LengthFilter(8)});
        mTitleTextView.setText(titleText);
        mTitleTextView.setTextColor(titleTextColor);
        mTitleTextView.setTextSize(titleTextSize);

        addView(mTitleTextView);

        mConstraintSet.constrainWidth(R.id.ctb_tv_title, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.constrainHeight(R.id.ctb_tv_title, ConstraintSet.WRAP_CONTENT);
        mConstraintSet.centerVertically(R.id.ctb_tv_title, ConstraintSet.PARENT_ID);
        mConstraintSet.centerHorizontally(R.id.ctb_tv_title, ConstraintSet.PARENT_ID);
    }
}
