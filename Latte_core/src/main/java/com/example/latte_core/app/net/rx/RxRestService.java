package com.example.latte_core.app.net.rx;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 请求接口
 */
public interface RxRestService {

    @GET
    Observable<String> get(@Url String url, @QueryMap Map<String, Object> params);


    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap Map<String, Object> params);


    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap Map<String, Object> params);


    @DELETE
    Observable<String> delete(@Url String url, @FieldMap Map<String, Object> params);

    @Streaming//防止文件过大造成内存溢出，一边下 一边写入内存
    @GET
    Observable<ResponseBody> download(@Url String url, @FieldMap Map<String, Object> params);

    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);

    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);

    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);


}
