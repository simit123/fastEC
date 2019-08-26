package com.example.latte_core.app.delegates;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

public abstract class BaseDelegate extends SupportFragment {



    public abstract Object setLayout();

    public abstract void onBindView(@Nullable Bundle savedInstanceState,View rootView);

    private Unbinder unbinder = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(),container,false);
        }
        else if (setLayout() instanceof View){
            rootView = (View) setLayout();
        }else {
            throw new IllegalStateException("layout is illegal!");
        }

        if (rootView != null) {
            onBindView(savedInstanceState,rootView);
            unbinder = ButterKnife.bind(this,rootView);
        }


        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
