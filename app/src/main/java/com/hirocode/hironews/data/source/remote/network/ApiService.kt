package com.hirocode.hironews.data.source.remote.network

import com.hirocode.hironews.BuildConfig.API_KEY
import com.hirocode.hironews.data.source.remote.response.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") sources: String = "id",
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}