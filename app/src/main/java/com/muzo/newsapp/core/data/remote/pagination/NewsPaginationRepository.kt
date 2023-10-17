package com.muzo.newsapp.core.data.remote.pagination

import androidx.paging.PagingData
import com.muzo.newsapp.core.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsPaginationRepository {


    fun getPaginationResult(
        pageSize:Int=1
    ): Flow<PagingData<Article>>

}