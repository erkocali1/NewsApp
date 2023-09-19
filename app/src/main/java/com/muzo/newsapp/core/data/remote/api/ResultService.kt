package com.muzo.newsapp.core.data.remote.api

import com.muzo.newsapp.core.constants.Constants.Companion.API_KEY
import com.muzo.newsapp.core.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ResultService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country") countryCode: String = "us",
        @Query("page") pageNumber: Int = 2,
        @Query("apiKey") apiKey: String = API_KEY,
        ): NewsResponse

    @GET("v2/top-headlines")
    suspend fun categoryForNews(
        @Query("country") searchQuery: String = "",
        @Query("page") pageNumber: Int = 1,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("category") category: String = "",
    ): NewsResponse

}