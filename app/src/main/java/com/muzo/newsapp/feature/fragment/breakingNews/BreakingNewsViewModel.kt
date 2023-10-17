package com.muzo.newsapp.feature.fragment.breakingNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.core.common.asReSource
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.domain.CategoryResultUseCase
import com.muzo.newsapp.domain.GetNewsUseCase
import com.muzo.newsapp.domain.GetPaginationResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase,
    private val categoryResultUseCase: CategoryResultUseCase,
    private val getPaginationResultUseCase: GetPaginationResultUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<GetNewsState> = MutableStateFlow(GetNewsState())
    val uiState = _uiState

    init {
        getNews()
    }

    private  fun getNews() {

        viewModelScope.launch {
            newsUseCase().asReSource().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(loading = false)
                    }

                    is Resource.Success -> {
                        _uiState.value =
                            _uiState.value.copy(loading = false, newsList = result.data)
                    }
                }
            }.launchIn(this)
        }

    }

    fun getCategoryNews(category:String){
        viewModelScope.launch {
            categoryResultUseCase(category).asReSource().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(loading = false)
                    }

                    is Resource.Success -> {
                        _uiState.value =
                            _uiState.value.copy(loading = false, categoryList = result.data)
                    }
                }
            }.launchIn(this)
        }
    }

    fun getPaginationResult(): Flow<PagingData<Article>> {
        return getPaginationResultUseCase.invoke()
    }

}

data class GetNewsState(
    val loading: Boolean = false,
    val newsList: NewsResponse? = null,
    val categoryList: NewsResponse? = null
)



