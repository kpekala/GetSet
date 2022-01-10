package org.example.data;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

//https://brickset.com/api/v3.asmx
public interface BrickSetService {

    @FormUrlEncoded
    @POST("login")
    Call<Map<String,String>> login(@Field("apiKey") String apiKey, @Field("username") String name, @Field("password") String password);
}
