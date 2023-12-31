package com.muzo.newsapp.core.data.remote.repository

import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.core.data.remote.source.NewsRemoteDataSource
import javax.inject.Inject

class NewsRemoteRepositoryImpl @Inject constructor(private val newsRemoteDataSource: NewsRemoteDataSource) :
    NewsRemoteRepository {
    override suspend fun result(): Result<NewsResponse> {
        return newsRemoteDataSource.result()
    }

    override suspend fun categoryResult(category: String): Result<NewsResponse> {
        return newsRemoteDataSource.categoryResult(category = category)
    }

    override suspend fun searchResult(search: String, page: Int): Result<NewsResponse> {

        return newsRemoteDataSource.searchResult(search, page)


    }
}