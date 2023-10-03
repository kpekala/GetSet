package com.kpekala.data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface BrickSetService {

    @FormUrlEncoded
    @POST("login")
    Call<Map<String,String>> login(@Field("apiKey") String apiKey,
                                   @Field("username") String name,
                                   @Field("password") String password);


    @GET("getSets")
    Call<Map<String,Object>> fetchSet(@Query("apiKey") String apiKey,
                                      @Query("userHash") String userHash,
                                      @Query("params") Map<String, Object> params);

    @GET("getSets")
    Call<Map<String,Object>> fetchSet1(@Query("apiKey") String apiKey,
                                      @Query("userHash") String userHash,
                                      @Query("params") String params);

    @GET
    Call<ResponseBody> fetchImage(@Url String imageUrl);
}
