package com.muzo.newsapp.core.data.remote.repository

import com.muzo.newsapp.core.data.model.NewsResponse

interface NewsRemoteRepository {

    suspend fun result():Result<NewsResponse>
}