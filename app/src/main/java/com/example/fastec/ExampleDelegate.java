package com.example.fastec;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.latte_core.app.delegates.LatteDelegate;
import com.example.latte_core.app.net.RestClient;
import com.example.latte_core.app.net.callback.IError;
import com.example.latte_core.app.net.callback.IFailure;
import com.example.latte_core.app.net.callback.IRequest;
import com.example.latte_core.app.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private void testRestClient(){
        RestClient.builder()
                .url("")
                .params("","")
                .onSuccess(response -> {

                }).onFailure(() -> {

                }).onError((code, msg) -> {

                }).onRequest(new IRequest() {
            @Override
            public void onRequestStart() {

            }

            @Override
            public void onRequestEnd() {

            }
        });
    }
}
