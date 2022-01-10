package org.example.data;

import org.example.login.LoginCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Map;

public class RetrofitWepApi {
    final String BASE_URL = "https://brickset.com/api/v3.asmx/";
    final String API_KEY = "3-Hm1r-1oyZ-oFVzp";
    private final BrickSetService brickSetService;
    public RetrofitWepApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        brickSetService = retrofit.create(BrickSetService.class);
    }

    public void login(String name, String password, LoginCallback callback){
        brickSetService.login(API_KEY,name,password).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.body() != null && response.body().get("hash") != null) {
                    callback.loginSuccessful(response.body().get("hash"));
                } else {
                    callback.loginFailed("response body is null");
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                callback.loginFailed(t.getMessage());
            }
        });
    }
}
