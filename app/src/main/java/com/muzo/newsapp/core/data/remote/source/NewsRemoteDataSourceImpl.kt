package com.muzo.newsapp.core.data.remote.source

import com.muzo.newsapp.core.constants.Constants.Companion.API_KEY
import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.core.data.remote.api.ResultService
import javax.inject.Inject

class NewsRemoteDataSourceImpl @Inject constructor(
    private val resultService: ResultService):NewsRemoteDataSource
 {
     override suspend fun result(): Result<NewsResponse> {
         return kotlin.runCatching {
             resultService.getBreakingNews("tr",1,"$API_KEY")
         }
     }

     override suspend fun everythingResult() {
         TODO("Not yet implemented")
     }
 }