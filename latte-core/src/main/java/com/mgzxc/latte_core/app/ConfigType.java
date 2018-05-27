package com.mgzxc.latte_core.app;

/**
 * Created by MG_ZXC on 2018/5/7.
 * 枚举类：在整个应用程序中是唯一的单例，并且只能初始化一次，换句话说在多线程操作的时候可以使用枚举类进行
 * 安全的惰性的单例初始化，相当于单例模式中的懒汉模式
 */
public enum  ConfigType {
    API_HOST,APPLICATION_CONTEXT,CONFIG_READ,ICON,INTERCEPTOR
}
