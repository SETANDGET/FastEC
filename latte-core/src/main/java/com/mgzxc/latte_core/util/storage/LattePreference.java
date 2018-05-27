package com.mgzxc.latte_core.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mgzxc.latte_core.app.Latte;


/**
 * Created by MG_ZXC on 2018/5/27.
 */
public final class LattePreference {
    /**
     * Activity.getPreferences(int mode)生成Activity名.xml用于Activity内部存贮
     * PreferenceManager.getDefaultSharedferences(Context)生成 包名_preferences.xml
     * Context.getSharedPreferences(String name,int mode)生成 name.xml
     */

    private static final SharedPreferences PREFERENCES = PreferenceManager.getDefaultSharedPreferences(Latte.getApplicationContext());
    private static final String APP_PREFERENCES_KEY = "profile";
    private static SharedPreferences getPreferences(){
        return PREFERENCES;
    }
    public static void setAppProfile(String val){
        getPreferences().edit()
                .putString(APP_PREFERENCES_KEY,val)
                .apply();
    }
    public static String getAppProfile(){
        return getPreferences().getString(APP_PREFERENCES_KEY, null);
    }
    public static JSONObject getAppProfileJson(){
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }
    public static void removeAppProfile(){
        getPreferences().edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }
    public static void clearAppPreferences(){
        getPreferences().edit()
                .clear()
                .apply();
    }
    public static void setAppFlag(String key,boolean flag){
        getPreferences().edit()
                .putBoolean(key,flag)
                .apply();

    }

    public static boolean getAppFlag(String key){
        return getPreferences()
                .getBoolean(key,false);
    }
    public static void addCustomAppProfile(String key,String val){
        getPreferences().edit().putString(key, val)
                .apply();
    }
    public static String getCustomAppProfile(String key){
        return getPreferences().getString(key, null);
    }
}
