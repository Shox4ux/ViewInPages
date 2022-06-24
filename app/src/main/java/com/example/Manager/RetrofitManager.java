package com.example.Manager;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.Listereners.OnFetchDataListener;
import com.example.Model.Root;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class RetrofitManager {
    Context context;
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);



    public RetrofitManager(Context context) {
        this.context = context;
    }

    public static final String url = "https://run.mocky.io/v3/";
    Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build();


    public void getAllSections(OnFetchDataListener listener){
        client.addInterceptor(interceptor);
        CallSections callSections = retrofit.create(CallSections.class);
        Call<Root> call = callSections.getRoot();
        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse( Call<Root> call,  Response<Root> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(context, "callback failed", Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onFetchData(response.body(),response.message());
            }

            @Override
            public void onFailure( Call<Root> call, Throwable t) {
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                listener.onError(t.getMessage());
            }
        });
    }





    public interface CallSections{
        @GET("8195267f-fc52-4357-88da-f6fd87b4000b")
        Call<Root> getRoot();
    }


}
