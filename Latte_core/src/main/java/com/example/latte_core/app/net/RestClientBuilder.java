package com.example.latte_core.app.net;


import android.content.Context;

import com.example.latte_core.app.net.callback.IError;
import com.example.latte_core.app.net.callback.IFailure;
import com.example.latte_core.app.net.callback.IRequest;
import com.example.latte_core.app.net.callback.ISuccess;
import com.example.latte_core.app.ui.LoadStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    //类变量最好加上m
    private  String mUrl = null;
    private  Map<String, Object> PARAMS = RestCreator.getParams();
    private  IRequest mRequest = null;
    private  ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private  IError mError = null;
    private  RequestBody mBody = null;
    private Context mContext = null;
    private LoadStyle mLoadStyle = null;
    private File mFile = null;
    private String mDownloadDir = null;
    private String mExtension = null;
    private String mName = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder downloadDir(String dir){
        this.mDownloadDir = dir;
        return this;
    }
    public final RestClientBuilder extension(String mExtension){
        this.mExtension = mExtension;
        return this;
    }
    public final RestClientBuilder fileName(String name){
        this.mName = name;
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

    /**
     *
     * @param file 文件名称
     * @return
     */
    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
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


    public final RestClientBuilder loader(Context context,LoadStyle style){
        this.mContext = context;
        this.mLoadStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoadStyle = LoadStyle.BallSpinFadeLoaderIndicator;
        return this;
    }



    public final RestClient build(){
        return new RestClient(mUrl, PARAMS,mRequest,mSuccess,mFailure,mError,mBody,mFile,mDownloadDir,mExtension,mName,mLoadStyle,mContext);
    }



}
