package com.mgzxc.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mgzxc.latte_core.app.Latte;

/**
 * Created by MG_ZXC on 2018/5/7.
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())
                .withApiHost("").configure();



    }
}
