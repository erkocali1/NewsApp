package com.muzo.newsapp.core.data.remote.repository

import com.muzo.newsapp.core.data.model.NewsResponse

interface NewsRemoteRepository {

    suspend fun result():Result<NewsResponse>
    suspend fun categoryResult(category:String):Result<NewsResponse>

    suspend fun searchResult(search:String,page:Int):Result<NewsResponse>
}