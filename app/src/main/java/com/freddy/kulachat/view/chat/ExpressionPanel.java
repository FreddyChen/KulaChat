package com.freddy.kulachat.view.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.freddy.kulachat.R;
import com.freddy.kulachat.config.AppConfig;
import com.freddy.kulachat.entity.ExpressionType;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.view.adapter.ExpressionTypeListAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/01 20:56
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ExpressionPanel extends LinearLayout {

    private Context mContext;
    private Unbinder unbinder;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private List<ExpressionType> mExpressionTypeList;
    private ExpressionTypeListAdapter mExpressionTypeListAdapter;
    private int keyboardHeight;

    public ExpressionPanel(@NonNull Context context) {
        this(context, null);
    }

    public ExpressionPanel(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpressionPanel(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_expression_panel, this, true);
        ButterKnife.bind(this, view);
        init();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        keyboardHeight = AppConfig.readKeyboardHeight();
        if(keyboardHeight == 0) {
            keyboardHeight = DensityUtil.dp2px(mContext, 247);
            AppConfig.saveKeyboardHeight(keyboardHeight);
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = keyboardHeight + DensityUtil.dp2px(mContext, 36);
        setLayoutParams(layoutParams);
    }

    private void init() {
        setOrientation(VERTICAL);
        initData();
        initRecyclerView();
    }

    private void initData() {
        mExpressionTypeList = new ArrayList<>();
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
        mExpressionTypeList.add(new ExpressionType(R.drawable.ic_expression_panel_tab_emoji, null));
    }

    private void initRecyclerView() {
        mExpressionTypeListAdapter = new ExpressionTypeListAdapter(R.layout.item_expression_type, mExpressionTypeList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mExpressionTypeListAdapter);
    }

    @OnClick(R.id.btn_test)
    void onTestBtnClick(View v) {
        Toasty.normal(mContext, "test", Toasty.LENGTH_SHORT).show();;
    }
}
