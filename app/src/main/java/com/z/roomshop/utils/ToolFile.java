package com.z.roomshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ZJer on 2017/5/1.
 */

public class ToolFile {

    /**
     * SharePreferences 保存名字
     */
    private static final String LOGIN_INFO = "user-config";

    private static SharedPreferences getDefaultSharePreferences(Context context) {
        return context.getSharedPreferences(LOGIN_INFO, Context.MODE_PRIVATE);
    }

    /**
     * 将int 类型的数据存入SharePreferences中
     * @param context   上下文环境
     * @param key   键
     * @param value 值
     */
    public static void putInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = getDefaultSharePreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 从SharePreferences 中获取int 类型的值
     * @param context   上下文环境
     * @param key       键
     * @param defValue  默认值
     * @return          获取的值
     */
    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sharedPreferences = getDefaultSharePreferences(context);
        return sharedPreferences.getInt(key, defValue);
    }

    /**
     * 将String 类型的数据放入SharePreferences 中
     * @param context   上下文环境
     * @param key       键
     * @param value     值
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = getDefaultSharePreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 从SharePreferences 中获取 String 类型的值
     * @param context   上下文环境
     * @param key       键
     * @param defValue  默认值
     * @return          获取的值
     */
    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sharedPreferences = getDefaultSharePreferences(context);
        return sharedPreferences.getString(key, defValue);
    }

    /**
     * 将Boolean 类型的值放入SharePreferences 中
     * @param context   上下文环境
     * @param key       键
     * @param value     值
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = getDefaultSharePreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 从SharePreferences 中获取Boolean 类型的值
     * @param context   上下文环境
     * @param key       键
     * @param defValue  默认值
     * @return          获取的值
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sharedPreferences = getDefaultSharePreferences(context);
        return sharedPreferences.getBoolean(key, defValue);
    }
}
