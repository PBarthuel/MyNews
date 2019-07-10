package com.example.mynews;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static RetrofitService sInstance;
    private Retrofit retrofit;

    private RetrofitService() {

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    @NonNull
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request request = chain.request();

                        HttpUrl url = request.url().newBuilder()
                                .addQueryParameter("api-key", "5A6K8wBcTBzAf39MXVBC9IBC1K7bAdP4")
                                .build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static RetrofitService getInstance() {
        if (sInstance == null) {
            sInstance = new RetrofitService();
        }
        return sInstance;
    }


    public <T> T create(Class<T> clazz) {
        return retrofit.create(clazz);
    }
}
