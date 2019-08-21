package com.example.latte_core.app.net;


import com.example.latte_core.app.net.callback.IError;
import com.example.latte_core.app.net.callback.IFailure;
import com.example.latte_core.app.net.callback.IRequest;
import com.example.latte_core.app.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    //类变量最好加上m
    private  String mUrl;
    private  Map<String, Object> PARAMS = RestCreator.getParams();
    private  IRequest mRequest;
    private  ISuccess mSuccess;
    private IFailure mFailure;
    private  IError mError;
    private  RequestBody mBody;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> mParams){
        mParams.putAll(mParams);
        return this;
    }

    public final RestClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder onSuccess(ISuccess iSuccess){
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder onFailure(IFailure iFailure){
        this.mFailure = iFailure;
        return this;
    }


    public final RestClientBuilder onError(IError iError){
        this.mError = iError;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mRequest = iRequest;
        return this;
    }


    public final RestClient build(){

        return new RestClient(mUrl, PARAMS,mRequest,mSuccess,mFailure,mError,mBody);

    }



}
