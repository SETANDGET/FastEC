package com.mgzxc.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mgzxc.latte_core.app.Latte;
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
                .withApiHost("http://10.0.2.2:8080/").configure();



    }
}
