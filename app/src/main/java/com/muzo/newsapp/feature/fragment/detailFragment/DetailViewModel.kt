package com.muzo.newsapp.feature.fragment.detailFragment

import com.muzo.newsapp.core.data.local.source.LocalNewsDataSource
import com.muzo.newsapp.core.data.model.Article
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val localDataSource: LocalNewsDataSource) {


    suspend fun saveNews(newsList: List<Article>) {
        localDataSource.insertNews(newsList)
    }

    suspend fun deleteNews(newsId: Int) {
        localDataSource.deleteNewsByUid(newsId)
    }

}