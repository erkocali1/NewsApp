package com.muzo.newsapp.core.data.local.source

import com.muzo.newsapp.core.data.model.Article
import kotlinx.coroutines.flow.Flow


interface LocalNewsDataSource {

    suspend fun getLocalNewsList(): Flow<List<Article>>

    suspend fun insertNews(newsList:List<Article>)

    suspend fun deleteNewsByUid(newsTittle:String)
}