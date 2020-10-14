package com.freddy.kulachat.model.user;

import android.content.Intent;

import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.config.AppConfig;
import com.freddy.kulachat.database.DatabaseManagerFactory;
import com.freddy.kulachat.entity.User;
import com.freddy.kulachat.manager.DelayManager;
import com.freddy.kulachat.utils.StringUtil;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.CActivityManager;
import com.freddy.kulachat.view.user.LoginActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserManager {

    private static volatile UserManager instance;
    private User mCurrentUser;
    private final Map<Long, User> USERS_CACHE = new HashMap<>();
    private IUserLoginStateListener mUserLoginStateListener = new UserLoginStateListener();

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) {
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }

        return instance;
    }

    public void setToken(String token) {
        AppConfig.saveUserToken(token);
    }

    public String getToken() {
        return AppConfig.readUserToken();
    }

    public void setUserId(Long userId) {
        AppConfig.saveUserId(userId);
    }

    public Long getUserId() {
        return AppConfig.readUserId();
    }

    public void setCurrentUser(User user) {
        getInstance().mCurrentUser = user;
        updateLocalUser(user);
    }

    public User getCurrentUser() {
        if (mCurrentUser == null) {
            Long userId = getUserId();
            if (userId != null) {
                mCurrentUser = DatabaseManagerFactory.getDatabaseManager().queryUser(userId);
            }
        }

        return mCurrentUser;
    }

    private void updateLocalUser(User user) {
        if (user == null) return;
        USERS_CACHE.put(user.getUserId(), user);
        if (user.getUserId() != null && user.getUserId().equals(getUserId())) {
            mCurrentUser = user;
        }
        DatabaseManagerFactory.getDatabaseManager().saveUser(user);
    }

    public void onUserLoggedIn(String token, User user, boolean initDatabase) {
        if (mUserLoginStateListener != null)
            mUserLoginStateListener.onUserLoggedIn(token, user, initDatabase);
    }

    public void onUserLogout() {
        if (mUserLoginStateListener != null) mUserLoginStateListener.onUserLogout();
    }

    public boolean isLoggedIn() {
        return StringUtil.isNotEmpty(getToken()) && StringUtil.isNotEmpty(getUserId());
    }

    public boolean checkUserLoginStateFromLocal() {
        if (isLoggedIn()) {
            DatabaseManagerFactory.getDatabaseManager().init();
            onUserLoggedIn(getToken(), getCurrentUser(), false);
            return true;
        }

        onUserLogout();
        return false;
    }

    private void gotoLogin() {
        BaseActivity activity = CActivityManager.getInstance().getTopActivityInStack();
        if (activity != null) {
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            if(!(activity instanceof LoginActivity)) {
                DelayManager.getInstance().startDelay(500, TimeUnit.MILLISECONDS, () -> {
                    CActivityManager.getInstance().finishOtherActivity(activity);
                });
            }
        }
    }

    public boolean isCompletedInfo() {
        if(mCurrentUser == null) return false;
        return mCurrentUser.isCompletedInfo();
    }

    public interface IUserLoginStateListener {
        void onUserLoggedIn(String token, User user, boolean initDatabase);

        void onUserLogout();
    }

    public static class UserLoginStateListener implements IUserLoginStateListener {

        @Override
        public void onUserLoggedIn(String token, User user, boolean initDatabase) {
            UserManager.getInstance().setToken(token);
            UserManager.getInstance().setUserId(user.getUserId());
            if (initDatabase) {
                DatabaseManagerFactory.getDatabaseManager().init();
            }
            UserManager.getInstance().setCurrentUser(user);
            if(user.isCompletedInfo()) {
                KulaApp.getInstance().connectIMS();
            }
        }

        @Override
        public void onUserLogout() {
            UserManager.getInstance().setToken(null);
            UserManager.getInstance().setUserId(null);
            UserManager.getInstance().setCurrentUser(null);
            DatabaseManagerFactory.getDatabaseManager().release();
            KulaApp.getInstance().disconnectIMS();
        }
    }
}
