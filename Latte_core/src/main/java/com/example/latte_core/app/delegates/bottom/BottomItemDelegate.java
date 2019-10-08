package com.example.latte_core.app.delegates.bottom;

import android.view.KeyEvent;
import android.view.View;

import com.example.latte_core.app.delegates.LatteDelegate;
import com.example.latte_core.app.utils.ToastUtils;

public abstract class BottomItemDelegate extends LatteDelegate implements View.OnKeyListener {

    private long mExitTime = 0;
    private static final int EXIT_TIME = 2000;


    @Override
    public void onResume() {
        super.onResume();
        //让fragment获取焦点并且设置key事件
        final View rootView = getView();
        if (rootView != null) {
            rootView.setFocusableInTouchMode(true);
            rootView.requestFocus();
            rootView.setOnKeyListener(this);
        }
    }

    @Override
    public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

        if (keyCode == KeyEvent.KEYCODE_BACK && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - mExitTime) > EXIT_TIME) {
                ToastUtils.showToast(_mActivity, "再按一次退出app");
            }
            mExitTime = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
