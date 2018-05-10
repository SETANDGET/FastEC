package com.mgzxc.latte_core.app;

import android.content.Context;

/**
 * Created by MG_ZXC on 2018/5/7.
 * 对外工具类 初始化配置
 */
public final class Latte {
    public static Configurator init(Context context){
        getConfigurator().getLatteConfigs().put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }
    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }
    public static <T> T getConfiguration(Object key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Context getApplicationContext(){
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

}
