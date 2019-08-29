package com.example.latte_core.app.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;

import com.example.latte_core.R;
import com.example.latte_core.app.Latte;
import com.example.latte_core.app.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

public abstract class ProxyActivity extends SupportActivity {
    private static final String TAG = "ProxyActivity";

    public abstract LatteDelegate setRootDelegate();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    @SuppressLint("RestrictedApi")
    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
