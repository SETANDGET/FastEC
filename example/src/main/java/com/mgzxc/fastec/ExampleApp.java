package com.mgzxc.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mgzxc.latte_core.app.Latte;
import com.mgzxc.latte_core.net.interceptors.DebugInterceptor;
import com.mgzxc.latte_ec.database.DatabaseManager;
import com.mgzxc.latte_ec.icon.FontEcModule;

/**
 * Created by MG_ZXC on 2018/5/7.
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withApiHost("http://10.0.3.2:8080/")//原生模拟器 http://10.0.2.2:8080/
                //.withApiHost("http://127.0.0.1/")
                .configure();
        DatabaseManager.getInstance().init(this);


    }
}
