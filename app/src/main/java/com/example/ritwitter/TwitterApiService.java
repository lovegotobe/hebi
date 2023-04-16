package com.example.ritwitter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface TwitterApiService {
    @GET
    Call<ResponseBody> downloadVideo(@Url String url);
}