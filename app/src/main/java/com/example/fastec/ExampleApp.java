package com.example.fastec;

import android.app.Application;

import com.example.latte.ec.icon.FontEcModel;
import com.example.latte_core.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Author: WuYang
 * Date:2019/8/6
 * Description: application
 */

public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化采用这种配置方式
        Latte.init(this).withApiHost("")
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModel()).configure();
    }
}
