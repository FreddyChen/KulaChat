package com.freddy.kulachat.view.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.freddy.kulachat.view.user.ContactFragment;
import com.freddy.kulachat.view.chat.MessageFragment;
import com.freddy.kulachat.view.user.MineFragment;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/29 20:50
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class HomeFragmentStateAdapter extends FragmentStateAdapter {

    private static final String[] PAGES = {
//            HomeFragment.class.getSimpleName(),
            MessageFragment.class.getSimpleName(),
            ContactFragment.class.getSimpleName(),
            MineFragment.class.getSimpleName(),
    };

    public HomeFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("HomeFragmentStateAdapter()", "createFragment() position = " + position);
        switch (position) {
            /*case 0:
                return HomeFragment.newInstance();*/
            case 0:
                return MessageFragment.newInstance();
            case 1:
                return ContactFragment.newInstance();
            case 2:
                return MineFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return PAGES.length;
    }
}
