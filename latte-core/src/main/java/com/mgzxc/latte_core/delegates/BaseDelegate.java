package com.mgzxc.latte_core.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by MG_ZXC on 2018/5/8.
 */
public abstract class BaseDelegate extends SwipeBackFragment{
    @SuppressWarnings("SpellCheckingInspection")
    private Unbinder mUnbinder = null;
    public abstract Object setLayout();//子类提供布局视图
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=null;
        if(setLayout() instanceof Integer){
            inflater.inflate(((Integer) setLayout()), container, false);
        }else if(setLayout() instanceof View){
            rootView= ((View) setLayout());
        }
        //绑定View
        if (rootView!=null){
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);
        }
        return rootView;
    }

    protected abstract void onBindView(@Nullable Bundle savedInstanceState, View rootView);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mUnbinder!=null) {
            mUnbinder.unbind();//解除绑定
        }
    }
}
