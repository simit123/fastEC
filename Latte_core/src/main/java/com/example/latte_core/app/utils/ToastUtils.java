package com.example.latte_core.app.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public final class ToastUtils {


    public static void showToast(Context mContext,String msg){
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
    }

}
