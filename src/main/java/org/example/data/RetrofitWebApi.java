package org.example.data;

import okhttp3.ResponseBody;
import org.example.data.model.SetModel;
import org.example.login.LoginCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RetrofitWebApi {
    final String BASE_URL = "https://brickset.com/api/v3.asmx/";
    final String API_KEY = "3-Hm1r-1oyZ-oFVzp";
    private final BrickSetService brickSetService;
    public RetrofitWebApi(){
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
    public void fetchSetData(String setId, String userHash, FetchSetCallback callback){
        String params = "{'setNumber': '" + setId + "-1'}";
        brickSetService.fetchSet1(API_KEY,userHash,params).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                System.out.println("fetchSetData: onResponse");
                var data = response.body();
                if(data == null)
                    callback.onFetchError("data is null");
                try{
                    Map<String, Object> setData = ((List<Map<String, Object>>) data.get("sets")).get(0);
                    System.out.println("siema");
                    SetModel setModel = ModelConverter.convertMap(setData);
                    callback.onFetchSuccessful(setModel);
                } catch (NullPointerException exception){
                    callback.onFetchError("Something is no yes");
                }catch (ClassCastException exception) {
                    callback.onFetchError("Class cast  exception");
                    exception.printStackTrace();
                }
                catch (Exception exception){
                    callback.onFetchError("Exception");
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                callback.onFetchError("error");
            }
        });
    }

    public void fetchImageSet(String url, FetchImageCallback callback){
         brickSetService.fetchImage(url).enqueue(new Callback<>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 if (response.isSuccessful()) {
                     if (response.body() != null) {
                         // display the image data in a ImageView or save it
                         InputStream imageInputStream = response.body().byteStream();
                         callback.onFetchImageSuccessful(imageInputStream);
                     } else {
                         callback.onFetchImageFailed();
                     }
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
