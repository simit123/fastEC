package com.example.latte_core.app.utils.timer;

import android.animation.TimeAnimator;

import java.sql.Time;
import java.util.TimerTask;

public class BaseTimerTask extends TimerTask {


    private ITimerListener iTimerListener = null;

    public BaseTimerTask(ITimerListener iTimerListener) {
        this.iTimerListener = iTimerListener;
    }

    @Override
    public void run() {
        if (iTimerListener != null) {
            iTimerListener.onTimer();
        }
    }
}
