package com.tw.androidbasicsapp.api

import com.tw.androidbasicsapp.models.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {

    @GET("News")
    fun getNewsFeeds(): Call<List<News>>

    @GET("News/{id}")
    fun getNewsFeed(@Path("id") id:Int): Call<News>

    companion object {
        fun create(): Api {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://10.0.2.2:8080/")
                .build()
            return retrofit.create(Api::class.java)
        }
    }
}