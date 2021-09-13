package com.example.cooka.retrofit

import com.example.cooka.model.RecipeDatas
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET ("recipes/v2")
    fun getDatas(
        @Query("type") type:String,
        @Query("q") q:String="salmon",
        @Query("app_id") app_id:String="a03c43af",
        @Query("app_key") app_key:String="e5d79dffe2b66474ff75017f1e0f357d"
    ):Call<RecipeDatas>
}