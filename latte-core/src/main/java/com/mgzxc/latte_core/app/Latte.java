package com.mgzxc.latte_core.app;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by MG_ZXC on 2018/5/7.
 * 对外工具类 初始化配置
 */
public final class Latte {
    public static Configurator init(Context context){
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

}
