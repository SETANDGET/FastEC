package com.mgzxc.latte_core.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by MG_ZXC on 2018/5/9.
 * 官方的生成loading 方式是通过反射机制的 但是在 项目中经常加载的就经常反射这样会严重影响效率
 * 解决办法：通过WeakHashMap 缓存机制
 */
final class LoaderCreator {
    //以缓存方式存贮
    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADING_MAP.get(type)==null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String name){
        if (name==null||name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();
        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return ((Indicator) drawableClass.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
