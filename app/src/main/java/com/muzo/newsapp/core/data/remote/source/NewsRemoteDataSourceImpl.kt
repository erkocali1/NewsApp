package com.muzo.newsapp.core.data.remote.source

import com.muzo.newsapp.core.constants.Constants.Companion.API_KEY
import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.core.data.remote.api.ResultService
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val resultService: ResultService
) : NewsRemoteDataSource {
    override suspend fun result(): Result<NewsResponse> {
        return kotlin.runCatching {
            resultService.getBreakingNews()
        }
    }

    override suspend fun categoryResult(category: String): Result<NewsResponse> {
        return kotlin.runCatching {
            resultService.categoryForNews(category = category)
        }
    }


}