package com.freddy.kulachat.config;

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
                0
        );
    }
}
