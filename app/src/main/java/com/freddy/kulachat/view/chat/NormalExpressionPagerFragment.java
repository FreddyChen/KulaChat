package com.freddy.kulachat.view.chat;

import android.os.Bundle;
import android.view.View;

import com.freddy.kulachat.R;
import com.freddy.kulachat.entity.Expression;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.view.BaseFragment;
import com.freddy.kulachat.view.adapter.ExpressionListAdapter;
import com.freddy.kulachat.widget.TopPaddingDecoration;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/04 15:37
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class NormalExpressionPagerFragment extends BaseFragment<NullablePresenter> {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private static final String KEY_EXPRESSION_LIST = "key_expression_list";
    private List<Expression> mExpressionList;
    private GridLayoutManager mLayoutManager;

    public static NormalExpressionPagerFragment newInstance(List<Expression> expressionList) {
        Bundle args = new Bundle();
        args.putSerializable(KEY_EXPRESSION_LIST, (Serializable) expressionList);
        NormalExpressionPagerFragment fragment = new NormalExpressionPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setRootView(Bundle saveInstanceState) {
        setLayout(R.layout.fragment_normal_expression_pager);
    }

    @Override
    protected void init() {
        Bundle bundle = getArguments();
        mExpressionList = (List<Expression>) bundle.getSerializable(KEY_EXPRESSION_LIST);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(getActivity(), 7);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.addItemDecoration(new TopPaddingDecoration(DensityUtil.dp2px(24)));
        ExpressionListAdapter adapter = new ExpressionListAdapter(R.layout.item_expression, mExpressionList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void setListeners() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                try {
                    int spanCount = mLayoutManager.getSpanCount();
                    int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
                    if((lastVisibleItemPosition + 1) % spanCount == 0 || (lastVisibleItemPosition + 2) % spanCount == 0) {
                        View view;
                        if((lastVisibleItemPosition + 1) % spanCount == 0) {
                            view = mLayoutManager.findViewByPosition(lastVisibleItemPosition);
                            view.setVisibility(View.GONE);
                        }

                        view = mLayoutManager.findViewByPosition(lastVisibleItemPosition - 1);
                        view.setVisibility(View.GONE);
                        view = mLayoutManager.findViewByPosition(lastVisibleItemPosition - spanCount);
                        view.setVisibility(View.GONE);
                        view = mLayoutManager.findViewByPosition(lastVisibleItemPosition - spanCount - 1);
                        view.setVisibility(View.GONE);
                    }

                    int lastCompletelyVisibleItemPosition = mLayoutManager.findLastCompletelyVisibleItemPosition();
                    if((lastCompletelyVisibleItemPosition + 1) % spanCount == 0 || (lastCompletelyVisibleItemPosition + 2) % spanCount == 0) {
                        View view;
                        if((lastCompletelyVisibleItemPosition + 1) % spanCount == 0) {
                            view = mLayoutManager.findViewByPosition(lastVisibleItemPosition - spanCount * 2);
                            view.setVisibility(View.VISIBLE);
                        }

                        view = mLayoutManager.findViewByPosition(lastVisibleItemPosition - spanCount * 2 - 1);
                        view.setVisibility(View.VISIBLE);
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}