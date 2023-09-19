package com.muzo.newsapp.domain

import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.core.data.remote.repository.NewsRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class CategoryResultUseCase @Inject constructor(private val categoryRemoteRepository: NewsRemoteRepository) {

    operator fun invoke(category:String): Flow<NewsResponse>{
        return flow {
            val result=categoryRemoteRepository.categoryResult(category = category)
            (result.getOrNull() ?: throw IllegalArgumentException("error message aliiiiiiiii")).also {
                emit(it)
            }
        }
    }
}