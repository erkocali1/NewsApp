package com.muzo.newsapp.core.data.local.source

import com.muzo.newsapp.core.data.local.room.NewsDao
import com.muzo.newsapp.core.data.model.Article
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalNewsDataSourceImpl @Inject constructor(private val newsDao: NewsDao) :
    LocalNewsDataSource {
    override suspend fun getLocalNewsList(): Flow<List<Article>> {
        return newsDao.getAllMusic()
    }

    override suspend fun insertNews(newsList: List<Article>) {
        return newsDao.insertNews(newsList)
    }

    override suspend fun deleteNewsByUid(newsUid: Int) {
        return newsDao.deleteNewsById(newsUid)
    }


}