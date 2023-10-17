package com.muzo.newsapp.core.data.remote.pagination

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.core.data.remote.api.ResultService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsPaginationRepositoryImpl @Inject constructor(private val resultService: ResultService) :
    NewsPaginationRepository {


    override fun getPaginationResult(pageSize: Int): Flow<PagingData<Article>> {
        return Pager(config = PagingConfig(
            pageSize = pageSize, enablePlaceholders = false
        ), pagingSourceFactory = {
            NewsPagingSource(
                resultService = resultService, pageSize = pageSize

            )
        }).flow
    }
}