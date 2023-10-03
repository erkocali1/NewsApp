package com.muzo.newsapp.domain

import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.core.data.remote.repository.NewsRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchResultUseCase @Inject constructor(private val newsRemoteRepository: NewsRemoteRepository) {


    operator fun invoke(search: String, page: Int): Flow<NewsResponse> {
        return flow {
            val result = newsRemoteRepository.searchResult(search, page = page)
            (result.getOrNull() ?: throw IllegalArgumentException("error message")).also {
                emit(it)
            }
        }
    }
}