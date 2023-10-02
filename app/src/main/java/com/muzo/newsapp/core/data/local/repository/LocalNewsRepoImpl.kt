package com.muzo.newsapp.core.data.local.repository

import com.muzo.newsapp.core.data.local.source.LocalNewsDataSource
import com.muzo.newsapp.core.data.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalNewsRepoImpl @Inject constructor(private val newsDataSource: LocalNewsDataSource) :
    LocalNewsRepo {
    override suspend fun saveNews(newsList: List<Article>) {
        return newsDataSource.insertNews(newsList)
    }

    override suspend fun deleteNewsByUid(newsTittle: String) {
        return newsDataSource.deleteNewsByUid(newsTittle)
    }

    override suspend fun getAllDataFromRoom(): Flow<List<Article>> {
        return newsDataSource.getLocalNewsList()
    }


}