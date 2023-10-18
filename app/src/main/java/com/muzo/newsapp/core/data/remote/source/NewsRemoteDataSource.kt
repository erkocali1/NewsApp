package com.muzo.newsapp.core.data.remote.source

import com.muzo.newsapp.core.data.model.NewsResponse

interface NewsRemoteDataSource {

    suspend fun result(): Result<NewsResponse>
    suspend fun categoryResult(category:String):Result<NewsResponse>

    suspend fun  searchResult(search:String,page:Int):Result<NewsResponse>


}