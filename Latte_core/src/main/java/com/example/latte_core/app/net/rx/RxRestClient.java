package com.example.latte_core.app.net.rx;

import android.content.Context;

import com.example.latte_core.app.net.HttpMethod;
import com.example.latte_core.app.net.RestClientBuilder;
import com.example.latte_core.app.net.RestCreator;
import com.example.latte_core.app.net.RestService;
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

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Url;

/**
 * 网络请求框架  restful
 */
public class RxRestClient {


    private final String URL;
    private final WeakHashMap<String, Object> PARAMS;
    private final RequestBody BODY;
    private final LoadStyle LOADER_STYLE;
    private final Context context;
    private final File FILE;




    public RxRestClient(String url, WeakHashMap<String, Object> params, RequestBody body,
                        File file, LoadStyle loadStyle, Context context) {
        this.URL = url;
        this.PARAMS = params;
        this.BODY = body;
        this.LOADER_STYLE = loadStyle;
        this.context = context;
        this.FILE = file;
    }

    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }


    private Observable<String> request(HttpMethod method){
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(context,LOADER_STYLE);
        }

        switch (method){
            case GET:
                observable = service.get(URL,PARAMS);
                break;
            case PUT:
                observable = service.put(URL,PARAMS);
                break;
            case DELETE:
                observable = service.delete(URL,PARAMS);
                break;
            case POST:
                observable = service.post(URL,PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL,BODY);
                break;
            case PUT_RAW:
                observable = service.postRaw(URL,BODY);
                break;
            case UPLOAD://文件上传
                //???
                final RequestBody requestBody = RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()),FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file",FILE.getName(),requestBody);
                observable = service.upload(URL,body);
                break;
                default:
                    break;
        }
        return observable;

    }

    public final Observable<String>  get(){
        return request(HttpMethod.GET);
    }


    public final Observable<String>  post(){
        if (BODY == null) {
            return request(HttpMethod.POST);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String>  delete(){
        return request(HttpMethod.DELETE);
    }

    public final Observable<String>  put(){
        if (BODY == null) {
            return request(HttpMethod.PUT);
        }else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    /***
     * 上传
     */
    public final Observable<String>  upLoad(){
        return request(HttpMethod.UPLOAD);
    }



    public final Observable<ResponseBody>  download(){
            return RestCreator.getRxRestService().download(URL,PARAMS);
    }
}
