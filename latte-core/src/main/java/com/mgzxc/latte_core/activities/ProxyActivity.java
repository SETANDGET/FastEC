package com.mgzxc.latte_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.mgzxc.latte_core.R;
import com.mgzxc.latte_core.delegates.LatteDelegae;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by MG_ZXC on 2018/5/7.
 */
public abstract class ProxyActivity extends SupportActivity{
    public abstract LatteDelegae setRootDelegate();//设置根fragment

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }
    public void initContainer(@Nullable Bundle savedInstanceState){
        ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState==null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());//加载Delegate
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
