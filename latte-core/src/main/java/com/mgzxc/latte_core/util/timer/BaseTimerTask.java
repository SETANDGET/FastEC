package com.mgzxc.latte_core.util.timer;

import java.util.TimerTask;

/**
 * Created by MG_ZXC on 2018/5/27.
 */
public class BaseTimerTask extends TimerTask {
    private ITimerListener mITimerListener = null;
    public BaseTimerTask(ITimerListener mITimerListener) {
        this.mITimerListener = mITimerListener;
    }
    @Override
    public void run() {
        if (mITimerListener!=null) {
            mITimerListener.onTimer();
        }
    }
}
