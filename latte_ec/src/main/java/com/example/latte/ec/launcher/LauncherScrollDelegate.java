package com.example.latte.ec.launcher;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.latte.ec.R;
import com.example.latte_core.app.Latte;
import com.example.latte_core.app.delegates.LatteDelegate;
import com.example.latte_core.app.ui.launcher.LauncherHolderCreator;
import com.example.latte_core.app.ui.launcher.ScrollLauncherTag;
import com.example.latte_core.app.utils.storage.LattePreference;

import java.util.ArrayList;
import java.util.concurrent.atomic.LongAccumulator;

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {



    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> res = new ArrayList<>();

    private void initBanner(){

        for (int i = 0; i < 4; i++) {
            res.add(R.mipmap.ic_launcher);
        }

        mConvenientBanner.setPages(new LauncherHolderCreator(),res)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);

    }

    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == res.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录
        }
    }
}
