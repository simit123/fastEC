package com.example.fastec;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.latte_core.app.Latte;
import com.example.latte_core.app.delegates.LatteDelegate;
import com.example.latte_core.app.net.RestClient;
import com.example.latte_core.app.net.RestCreator;
import com.example.latte_core.app.net.RestService;
import com.example.latte_core.app.net.callback.IRequest;
import com.example.latte_core.app.net.rx.RxRestClient;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        testRestClient();
        rxTest2();
    }


    private void rxTest1(){
        //rxjava 两种调用方式 第一种
        final String url = "index.php";
        final WeakHashMap<String,Object> params = new WeakHashMap<>();
        final Observable<String> observable = RestCreator.getRxRestService().get(url,params);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(Latte.getApplicationContext(),s,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void rxTest2(){
        //rxjava 两种调用方式 第二种
        final String url = "index.php";
        final WeakHashMap<String,Object> params = new WeakHashMap<>();
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(Latte.getApplicationContext(),s,Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .onSuccess(response -> {
                    Toast.makeText(Latte.getApplicationContext(),response,Toast.LENGTH_LONG).show();

                }).onFailure(() -> {

                }).onError((code, msg) -> {

                }).onRequest(new IRequest() {
            @Override
            public void onRequestStart() {
                Toast.makeText(Latte.getApplicationContext(),"请求开始",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRequestEnd() {

            }
        }).build().get();
    }
}
