package com.muzo.newsapp.core.data.remote.repository

import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.core.data.remote.source.NewsRemoteDataSource
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(private val newsRemoteDataSource: NewsRemoteDataSource) :
    NewsRemoteRepository {
    override suspend fun result(): Result<NewsResponse> {
        return newsRemoteDataSource.result()
    }
}