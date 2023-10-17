package com.muzo.newsapp.domain

import com.muzo.newsapp.core.data.remote.pagination.NewsPaginationRepository
import javax.inject.Inject

class GetPaginationResultUseCase @Inject constructor(private val newsPaginationRepository: NewsPaginationRepository) {

    operator fun invoke()=newsPaginationRepository.getPaginationResult()



}