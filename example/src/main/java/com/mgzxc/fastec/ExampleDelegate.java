package com.mgzxc.fastec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mgzxc.latte_core.delegates.LatteDelegate;

/**
 * Created by MG_ZXC on 2018/5/8.
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    protected void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
