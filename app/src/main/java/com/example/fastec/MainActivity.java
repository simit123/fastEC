package com.example.fastec;


import android.widget.Toast;

import com.example.latte_core.app.Latte;
import com.example.latte_core.app.activitys.ProxyActivity;
import com.example.latte_core.app.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
////        Toast.makeText(Latte.getApplication(),"传入context了",Toast.LENGTH_LONG).show();
//    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
