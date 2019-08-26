package com.example.latte_core.app.net;

import android.content.Context;

import com.example.latte_core.app.Latte;
import com.example.latte_core.app.net.callback.IError;
import com.example.latte_core.app.net.callback.IFailure;
import com.example.latte_core.app.net.callback.IRequest;
import com.example.latte_core.app.net.callback.ISuccess;
import com.example.latte_core.app.net.callback.RequestCallbacks;
import com.example.latte_core.app.ui.LatteLoader;
import com.example.latte_core.app.ui.LoadStyle;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.internal.connection.ConnectInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Url;

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
    private final LoadStyle LOADER_STYLE;
    private final Context context;

    public RestClient(String url, Map<String, Object> params, IRequest request, ISuccess success, IFailure failure, IError error, RequestBody body, LoadStyle loadStyle, Context context) {
        this.URL = url;
        this.PARAMS = params;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loadStyle;
        this.context = context;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }


    private void request(HttpMethod method){
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(context,LOADER_STYLE);
        }





        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        switch (method){
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
                default:
                    break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }


    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(REQUEST,SUCCESS,FAILURE,ERROR,LOADER_STYLE);
    }


    public final void get(){
        request(HttpMethod.GET);
    }


    public final void post(){
        request(HttpMethod.POST);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }
}
