package com.freddy.kulachat.view.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/29 20:50
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class HomeFragmentStateAdapter extends FragmentStateAdapter {

    private int num;

    public HomeFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity, int num) {
        super(fragmentActivity);
        this.num = num;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("HomeFragmentStateAdapter()", "createFragment() position = " + position);
        switch (position) {
            case 0:
                return Fragment1.newInstance();
            case 1:
                return Fragment2.newInstance();
            case 2:
                return Fragment3.newInstance();
            case 3:
                return Fragment4.newInstance();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return num;
    }
}
