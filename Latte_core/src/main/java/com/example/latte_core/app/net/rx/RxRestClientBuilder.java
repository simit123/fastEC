package com.example.latte_core.app.net.rx;


import android.content.Context;

import com.example.latte_core.app.net.RestCreator;
import com.example.latte_core.app.ui.loader.LoadStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RxRestClientBuilder {

    //类变量最好加上m
    private  String mUrl = null;
    private  WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private  RequestBody mBody = null;
    private Context mContext = null;
    private LoadStyle mLoadStyle = null;
    private File mFile = null;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> mParams){
        mParams.putAll(mParams);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
        PARAMS.put(key,value);
        return this;
    }

    public final RxRestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    /**
     *
     * @param file 文件名称
     * @return
     */
    public final RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoadStyle style){
        this.mContext = context;
        this.mLoadStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoadStyle = LoadStyle.BallSpinFadeLoaderIndicator;
        return this;
    }



    public final RxRestClient build(){
        return new RxRestClient(mUrl, PARAMS,mBody,mFile,mLoadStyle,mContext);
    }



}
