package com.example.inno_sampleproject.Interface;

import com.example.inno_sampleproject.Model.PageDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceAPIRequests
{
    @GET("users")
    Call<PageDataResponse> GetPageData(@Query("page") int page,@Query("per_page") int limit);

}
