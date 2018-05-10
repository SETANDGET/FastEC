package com.mgzxc.latte_core.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.mgzxc.latte_core.R;
import com.mgzxc.latte_core.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by MG_ZXC on 2018/5/9.
 */
public class LatteLoader {
    //对话框缩放大小
    private static final int LOAD_SIZE_SCALE = 8;
    //缓存存贮对话框
    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();
    private static final String DEFAULT_LOADER_STYLE = LoaderStyle.BallClipRotatePulseIndicator.name();

    //展示制定类型对话框
    public static void showLoading(Context context, Enum<LoaderStyle> type) {
        showLoading(context, type.name());
    }
    public static void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);
        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();
        if (dialogWindow!=null) {
            final WindowManager.LayoutParams layoutParams = dialogWindow.getAttributes();
            layoutParams.width=deviceWidth/LOAD_SIZE_SCALE;
            layoutParams.height = deviceHeight / LOAD_SIZE_SCALE;
            layoutParams.height = layoutParams.height + deviceHeight / LOAD_SIZE_SCALE;
            layoutParams.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();
    }
    //展示默认对话框
    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER_STYLE);
    }

    //关闭对话框
    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog!=null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                }
            }
        }
    }


}
