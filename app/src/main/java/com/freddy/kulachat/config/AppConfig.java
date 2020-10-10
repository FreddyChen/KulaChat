package com.freddy.kulachat.config;

import com.freddy.kulachat.utils.AES;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.StringUtil;
import com.freddy.kulachat.utils.preferences.PreferenceConfig;
import com.freddy.kulachat.utils.preferences.PreferencesHelper;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/02 02:38
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class AppConfig {

    public static void saveKeyboardHeight(int keyboardHeight) {
        PreferencesHelper.write(
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.KEYBOARD_HEIGHT,
                keyboardHeight
        );
    }

    public static int readKeyboardHeight() {
        return PreferencesHelper.readInt(
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.KEYBOARD_HEIGHT,
                DensityUtil.getScreenHeight() / 5 * 2 + DensityUtil.dp2px(30)
        );
    }

    public static void saveUserToken(String token) {
        if(StringUtil.isNotEmpty(token)) {
            PreferencesHelper.write(
                    PreferenceConfig.PREFERENCE_COMMON,
                    PreferenceConfig.USER_TOKEN,
                    AES.encrypt(token)
            );
        }else {
            PreferencesHelper.remove(
                    PreferenceConfig.PREFERENCE_COMMON,
                    PreferenceConfig.USER_TOKEN
            );
        }
    }

    public static String readUserToken() {
        String userToken = PreferencesHelper.readString(
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.USER_TOKEN,
                null
        );
        if (StringUtil.isEmpty(userToken)) return null;
        return AES.decrypt(userToken);
    }

    public static void saveUserId(Long userId) {
        if(userId != null) {
            PreferencesHelper.write(
                    PreferenceConfig.PREFERENCE_COMMON,
                    PreferenceConfig.USER_ID,
                    userId
            );
        }else {
            PreferencesHelper.remove(
                    PreferenceConfig.PREFERENCE_COMMON,
                    PreferenceConfig.USER_ID
            );
        }
    }

    public static Long readUserId() {
        long userId = PreferencesHelper.readLong(
                PreferenceConfig.PREFERENCE_COMMON,
                PreferenceConfig.USER_ID,
                0L);
        if(userId == 0L) return null;
        return userId;
    }
}
