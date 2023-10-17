package com.muzo.newsapp.domain

import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.core.data.remote.repository.NewsRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetNewsUseCase @Inject constructor(private val remoteRepository: NewsRemoteRepository) {

    operator fun invoke(): Flow<NewsResponse> {
        return flow  {
            val result =remoteRepository.result()
            (result.getOrNull() ?: throw IllegalArgumentException("error message")).also {
                emit(it)
            }
        }
    }


}