package com.freddy.kulachat.view.home;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;

import com.freddy.kulachat.R;
import com.freddy.kulachat.contract.home.HomeContract;
import com.freddy.kulachat.media.MediaScanner;
import com.freddy.kulachat.media.MediaType;
import com.freddy.kulachat.presenter.home.HomePresenter;
import com.freddy.kulachat.storage.UploadUtil;
import com.freddy.kulachat.storage.config.UploadFileOptions;
import com.freddy.kulachat.storage.config.UploadFileType;
import com.freddy.kulachat.storage.config.UploadFolderType;
import com.freddy.kulachat.storage.config.UploadType;
import com.freddy.kulachat.storage.listener.OnUploadFileListener;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.adapter.HomeFragmentStateAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.viewpager2.widget.ViewPager2;

import java.io.File;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    @BindView(R.id.view_pager)
    ViewPager2 mViewPager;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void init() {
        disableBottomNavigationMenuLongClick();
        HomeFragmentStateAdapter adapter = new HomeFragmentStateAdapter(activity);
        mViewPager.setAdapter(adapter);
        mViewPager.setUserInputEnabled(false);

        MediaScanner scanner = new MediaScanner(getApplicationContext());
        scanner.loadMedias(MediaType.Image);
    }

    private void upload() {
        File file = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera/IMG_20201013_174644.jpg");
        if(file.isFile() && file.exists()) {
            UploadFileOptions options = new UploadFileOptions.Builder()
                    .setFilePath(file.getPath())
                    .setUploadType(UploadType.Normal)
                    .setUploadFileType(UploadFileType.JPG_IMAGE)
                    .setUploadFolderType(UploadFolderType.AvatarImage)
                    .build();

            UploadUtil.uploadFile(options, new OnUploadFileListener() {

                @Override
                public void onStart(String filePath) {
                    Log.d("HomeActivity", "onStart() filePath = " + filePath);
                }

                @Override
                public void onProgress(String filePath, int progress) {
                    Log.d("HomeActivity", "onProgress() filePath = " + filePath + "\tprogress = " + progress);
                }

                @Override
                public void onSucceed(String filePath, String fileUrl) {
                    Log.d("HomeActivity", "onSucceed() filePath = " + filePath + "\tfileUrl = " + fileUrl);
                }

                @Override
                public void onFailed(String filePath, String errMsg) {
                    Log.d("HomeActivity", "onSucceed() filePath = " + filePath + "\terrMsg = " + errMsg);
                }

                @Override
                public void onFinished() {
                    Log.d("HomeActivity", "onFinished");
                }
            });
        }
    }

    @Override
    protected void setListeners() {
        mViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                mBottomNavigation.getMenu().getItem(position).setChecked(true);
            }
        });

        mBottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
//                case R.id.menu_home: {
//                    mViewPager.setCurrentItem(0, false);
//                    return true;
//                }

                case R.id.menu_message: {
                    mViewPager.setCurrentItem(0, false);
                    return true;
                }

                case R.id.menu_contact: {
                    mViewPager.setCurrentItem(1, false);
                    return true;
                }

                case R.id.menu_mine: {
                    mViewPager.setCurrentItem(2, false);
                    return true;
                }

            }
            return false;
        });
    }

    private void disableBottomNavigationMenuLongClick() {
        Menu menu = mBottomNavigation.getMenu();
        int menuCount = menu.size();
        for (int i = 0; i < menuCount; i++) {
            View view = mBottomNavigation.findViewById(menu.getItem(i).getItemId());
            if (view == null) {
                continue;
            }
            view.setOnLongClickListener(v -> true);
        }
    }

    @Override
    public void onTestSuccess() {
        Log.d("HomeActivity", "onTestSuccess()");
    }

    private long firstClickTime = 0L;

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            long secondClickTime = System.currentTimeMillis();
            if (secondClickTime - firstClickTime > 2000) {
                Toasty.warning(activity, "再按一次退出程序", Toasty.LENGTH_SHORT).show();
                firstClickTime = secondClickTime;
                return true;
            } else {
                finish();
            }
        }

        return super.onKeyUp(keyCode, event);
    }
}
