package com.example.latte_core.app.net;

import android.provider.Telephony;
import android.telecom.Call;

import com.example.latte_core.app.ConfigType;
import com.example.latte_core.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {


    private static final class ParamsHolder{
        public static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String,Object> getParams(){
        return ParamsHolder.PARAMS;
    }


    public static RestService getRestService(){
        return RestServiceHolder.REST_SERVICE;
    }

    private RestCreator(){}
    private static class Holder{
        private final static String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST.name());
        private final static Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OKHttpHolder.OKHTTP_CLIENT)
                .build();
    }


    private final static class OKHttpHolder{
        private final static int TIME_OUT = 60;
        private final static OkHttpClient OKHTTP_CLIENT = new OkHttpClient().newBuilder()
                .connectTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .build();
    }


    private final static class RestServiceHolder{
        private final static RestService REST_SERVICE = Holder.RETROFIT_CLIENT.create(RestService.class);
    }

}
