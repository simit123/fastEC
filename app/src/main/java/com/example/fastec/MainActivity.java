package com.example.fastec;


import android.app.ActionBar;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.latte.ec.launcher.LauncherDelegate;
import com.example.latte.ec.launcher.LauncherScrollDelegate;
import com.example.latte_core.app.Latte;
import com.example.latte_core.app.activitys.ProxyActivity;
import com.example.latte_core.app.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
////        Toast.makeText(Latte.getApplicationContext(),"传入context了",Toast.LENGTH_LONG).show();
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }
}
