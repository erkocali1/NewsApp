package com.muzo.newsapp.core.data.local.repository

import com.muzo.newsapp.core.data.model.Article
import kotlinx.coroutines.flow.Flow

interface LocalNewsRepo {

    suspend fun saveNews(newsList:List<Article>)

    suspend fun deleteNewsByUid(newsUid:Int)

    suspend fun getAllDataFromRoom(): Flow<List<Article>>
}