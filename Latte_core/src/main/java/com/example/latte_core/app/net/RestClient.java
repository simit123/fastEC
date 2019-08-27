package com.example.latte_core.app.net;

import android.content.Context;

import com.example.latte_core.app.net.callback.IError;
import com.example.latte_core.app.net.callback.IFailure;
import com.example.latte_core.app.net.callback.IRequest;
import com.example.latte_core.app.net.callback.ISuccess;
import com.example.latte_core.app.net.callback.RequestCallbacks;
import com.example.latte_core.app.net.download.DownLoadHandler;
import com.example.latte_core.app.ui.LatteLoader;
import com.example.latte_core.app.ui.LoadStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 网络请求框架  restful
 */
public class RestClient {


    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoadStyle LOADER_STYLE;
    private final Context context;
    private final File FILE;

    private final String DOWNLOAD_DIR;//文件目录
    private final String EXTENSION;//后缀名
    private final String NAME;



    public RestClient(String url, WeakHashMap<String, Object> params, IRequest request, ISuccess success, IFailure failure, IError error, RequestBody body,
                      File file,String downloadDir,String extension,String name, LoadStyle loadStyle, Context context) {
        this.URL = url;
        this.PARAMS = params;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.LOADER_STYLE = loadStyle;
        this.context = context;
        this.FILE = file;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
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
            case POST_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case PUT_RAW:
                call = service.postRaw(URL,BODY);
                break;
            case UPLOAD://文件上传
                //???
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                call = service.upload(URL,body);
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
        if (BODY == null) {
            request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }

    public final void put(){
        if (BODY == null) {
            request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.PUT_RAW);
        }
    }

    /***
     * 上传
     */
    public final void upLoad(){
        request(HttpMethod.UPLOAD);
    }



    public final void download(){
            new DownLoadHandler(URL,REQUEST,SUCCESS,FAILURE,ERROR,DOWNLOAD_DIR,EXTENSION,NAME);
    }
}
