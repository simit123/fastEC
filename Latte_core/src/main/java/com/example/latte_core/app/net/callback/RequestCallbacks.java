package com.example.latte_core.app.net.callback;

import android.os.Handler;

import com.example.latte_core.app.ui.LatteLoader;
import com.example.latte_core.app.ui.LoadStyle;

import java.util.concurrent.atomic.LongAccumulator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallbacks implements Callback<String> {
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final LoadStyle LOADER_STYLE;

    private static Handler handler = new Handler();

    public RequestCallbacks(IRequest iRequest, ISuccess iSuccess, IFailure iFailure, IError iError, LoadStyle loadStyle) {
        this.REQUEST = iRequest;
        this.SUCCESS = iSuccess;
        this.FAILURE = iFailure;
        this.ERROR = iError;
        this.LOADER_STYLE = loadStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if (ERROR != null) {
                ERROR.onError(response.code(),response.message());
            }
        }

        if (LOADER_STYLE != null) {
            stopLoading();
        }

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

        stopLoading();
        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }
    }


    private void stopLoading(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LatteLoader.dismissLoading();
            }
        },1000);
    }
}
