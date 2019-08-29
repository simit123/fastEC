package com.example.latte_core.app.ui.loader;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatDialog;

import com.example.latte_core.R;
import com.example.latte_core.app.utils.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

public class LatteLoader {

    private static final int LOAD_SIZE_SCALE = 8;//loading 根据屏幕大小缩放比
    private static final int LOAD_OFFSET_SCALE = 10;//loading 偏移量

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoadStyle.BallSpinFadeLoaderIndicator.name();

    public static void showLoading(Context context,String type){

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        dialog.setContentView(avLoadingIndicatorView);
        int deviceWith = DimenUtil.getScreenWith();
        int deviceHeight = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWith/LOAD_SIZE_SCALE;
            lp.height = deviceHeight/LOAD_SIZE_SCALE;

            lp.height = lp.height+deviceHeight/LOAD_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }

        LOADERS.add(dialog);
        dialog.show();

    }


    public static void showLoading(Context context,Enum<LoadStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context){
        showLoading(context,DEFAULT_LOADER);

    }


    public static void dismissLoading(){
        for (AppCompatDialog dialog:LOADERS){
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.cancel();
                    dialog.dismiss();
                }
            }
        }
        LOADERS.clear();
    }

}
