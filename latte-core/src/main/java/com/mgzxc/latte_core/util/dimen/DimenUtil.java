package com.mgzxc.latte_core.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.mgzxc.latte_core.app.Latte;

/**
 * Created by MG_ZXC on 2018/5/9.
 */
public final class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics metrics = resources.getDisplayMetrics();
        return metrics.heightPixels;
    }
}
