package com.example.latte_core.app.net;

import com.example.latte_core.app.net.callback.IError;
import com.example.latte_core.app.net.callback.IFailure;
import com.example.latte_core.app.net.callback.IRequest;
import com.example.latte_core.app.net.callback.ISuccess;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * 网络请求框架  restful
 */
public class RestClient {


    private final String URL;
    private final Map<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;

    public RestClient(String url, Map<String, Object> params, IRequest request, ISuccess success, IFailure failure, IError error, RequestBody body) {
        this.URL = url;
        this.PARAMS = params;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

}
