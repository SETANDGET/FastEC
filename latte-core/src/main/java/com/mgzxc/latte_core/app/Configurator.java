package com.mgzxc.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * Created by MG_ZXC on 2018/5/7.
 */
public class Configurator {
    //要比HashMap容易回收
    private static final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    //图标初始化
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();
    //私有构造
    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READ, false);
    }
    /**
     * 单例模式
     * 1、要么使用枚举
     * 2、懒汉模式（双重校验锁）
     * 3、静态类部类模式单例模式
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READ, true);
    }

    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }
    private void initIcons(){
        if (ICONS.size()>0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    private void checkConfigurations() {
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READ);
        if (!isReady) {
            throw new RuntimeException("");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Object key) {
        checkConfigurations();
        final Object value = LATTE_CONFIGS.get(key);
        if (value==null) {
            throw new NullPointerException(key.toString() + "is nulll");
        }
        return (T) LATTE_CONFIGS.get(key);
    }

}
