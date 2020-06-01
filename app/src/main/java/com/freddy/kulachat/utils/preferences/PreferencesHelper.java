package com.freddy.kulachat.utils.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.freddy.kulachat.KulaApp;

import java.util.Map;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/02 02:34
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class PreferencesHelper {

    private static SharedPreferences sSharedPreferences;

    private static void save(SharedPreferences.Editor editor) {
        editor.apply();
    }

    public static void write(String fileName, String k, int v) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(k, v);
        save(editor);
    }

    public static void write(String fileName, String k, long v) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(k, v);
        save(editor);
    }

    public static void write(String fileName, String k, boolean v) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(k, v);
        save(editor);
    }

    public static void write(String fileName, String k, String v) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(k, v);
        save(editor);
    }

    public static int readInt(String fileName, String k) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getInt(k, 0);
    }

    public static int readInt(String fileName, String k, int defv) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getInt(k, defv);
    }

    public static long readLong(String fileName, String k,
                                long defv) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getLong(k, defv);
    }

    public static boolean readBoolean(String fileName, String k) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getBoolean(k, false);
    }

    public static boolean readBoolean(String fileName, String k, boolean defBool) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getBoolean(k, defBool);
    }

    public static String readString(String fileName, String k) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getString(k, null);
    }

    public static String readString(String fileName, String k, String defV) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return preferences.getString(k, defV);
    }

    public static Map<String, ?> readAll(String fileName) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        Map<String, ?> map = preferences.getAll();
        return map;
    }

    public static void remove(String fileName, String k) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(k);
        save(editor);
    }

    public static void clean(String fileName) {
        SharedPreferences preferences = KulaApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        save(editor);
    }

}
