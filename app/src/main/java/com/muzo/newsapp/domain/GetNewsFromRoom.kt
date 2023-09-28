package com.muzo.newsapp.domain

import com.muzo.newsapp.core.data.local.repository.LocalNewsRepo
import com.muzo.newsapp.core.data.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNewsFromRoom @Inject constructor(private val localNewsRepo: LocalNewsRepo) {

    operator fun invoke(): Flow<List<Article>>{
        return flow {
            val result=localNewsRepo.getAllDataFromRoom()
            emitAll(result)
        }
    }
}