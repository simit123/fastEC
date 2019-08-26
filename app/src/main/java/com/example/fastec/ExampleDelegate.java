package com.example.fastec;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.latte_core.app.Latte;
import com.example.latte_core.app.delegates.LatteDelegate;
import com.example.latte_core.app.net.RestClient;
import com.example.latte_core.app.net.callback.IRequest;

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://news.baidu.com/")
                .loader(getContext())
                .onSuccess(response -> {
                    Toast.makeText(Latte.getApplication(),response,Toast.LENGTH_LONG).show();

                }).onFailure(() -> {

                }).onError((code, msg) -> {

                }).onRequest(new IRequest() {
            @Override
            public void onRequestStart() {
                Toast.makeText(Latte.getApplication(),"请求开始",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestEnd() {

            }
        }).build().get();
    }
}
