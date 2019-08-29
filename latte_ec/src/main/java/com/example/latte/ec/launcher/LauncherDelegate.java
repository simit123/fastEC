package com.example.latte.ec.launcher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.latte.ec.R;
import com.example.latte.ec.R2;
import com.example.latte_core.app.activitys.ProxyActivity;
import com.example.latte_core.app.delegates.LatteDelegate;
import com.example.latte_core.app.ui.launcher.ScrollLauncherTag;
import com.example.latte_core.app.utils.storage.LattePreference;
import com.example.latte_core.app.utils.timer.BaseTimerTask;
import com.example.latte_core.app.utils.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_timer)
    TextView tv_timer;

    private Timer timer;

    private int count = 5;


    @OnClick(R2.id.tv_timer)
    void onClickTimer(){
        if (timer != null) {
            timer.cancel();
            timer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){

        timer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        timer.schedule(task,0,1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }


    /**
     * 判断是否显示滑动启动页
     */
    private void checkIsShowScroll(){

        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登录了app
        }
    }




    @Override
    public void onTimer() {
        ProxyActivity proxyActivity = getProxyActivity();
        ((Activity) proxyActivity).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO: 2019/8/29  MessageFormat 这个类要记着 好东西
                tv_timer.setText(MessageFormat.format("跳过\n{0}s",count));
                count --;
                if (count < 0){
                    if (timer != null) {
                        timer.cancel();
                        timer = null;
                        checkIsShowScroll();
                    }
                }
            }
        });
    }
}
