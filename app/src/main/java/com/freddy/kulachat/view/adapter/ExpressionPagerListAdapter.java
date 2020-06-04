package com.freddy.kulachat.view.adapter;

import com.freddy.kulachat.entity.ExpressionType;
import com.freddy.kulachat.view.chat.NormalExpressionPagerFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/04 15:28
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ExpressionPagerListAdapter extends FragmentStateAdapter {

    private List<ExpressionType> mExpressionTypeList;

    public ExpressionPagerListAdapter(@NonNull FragmentActivity fragmentActivity, List<ExpressionType> expressionTypeList) {
        super(fragmentActivity);
        this.mExpressionTypeList = expressionTypeList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return NormalExpressionPagerFragment.newInstance(mExpressionTypeList.get(position).getExpressionList());
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mExpressionTypeList == null ? 0 : mExpressionTypeList.size();
    }
}
