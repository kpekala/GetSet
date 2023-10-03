package com.kpekala.data.rest;

import com.kpekala.data.model.ModelConverter;
import com.kpekala.data.model.SetModel;
import com.kpekala.login.LoginCallback;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class BricksetRestClient {
    final String BASE_URL = "https://brickset.com/api/v3.asmx/";
    private String apiKey;
    private final BrickSetService brickSetService;

    public BricksetRestClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        brickSetService = retrofit.create(BrickSetService.class);

        loadApiKey();
    }

    private void loadApiKey() {
        String resourceName = "app.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
            apiKey = props.getProperty("apikey");
            System.out.println(apiKey);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void login(String name, String password, LoginCallback callback) {
        brickSetService.login(apiKey, name, password).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.body() != null && response.body().get("hash") != null) {
                    callback.onLoginSuccessful(response.body().get("hash"));
                } else {
                    callback.onLoginFailed("response body is null");
                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                callback.onLoginFailed(t.getMessage());
            }
        });
    }

    public void fetchSetData(String setId, String userHash, FetchSetCallback callback) {
        String params = "{'setNumber': '" + setId + "-1'}";
        brickSetService.fetchSet(apiKey, userHash, params).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                Map<String, Object> data = response.body();
                if (data == null) {
                    callback.onFetchError("data is null");
                    return;
                }
                try {
                    Map<String, Object> setData = ((List<Map<String, Object>>) data.get("sets")).get(0);
                    SetModel setModel = ModelConverter.convertMap(setData);
                    callback.onFetchSuccessful(setModel);
                } catch (Exception exception) {
                    callback.onFetchError(exception.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                callback.onFetchError("error");
            }
        });
    }

    public void fetchImageSet(String url, FetchImageCallback callback) {
        brickSetService.fetchImage(url).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    InputStream imageInputStream = response.body().byteStream();
                    callback.onFetchImageSuccessful(imageInputStream);
                } else {
                    callback.onFetchImageFailed();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                callback.onFetchImageFailed();
            }
        });
    }
}
