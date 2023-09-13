package com.muzo.newsapp.feature.fragment.BreakingNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.core.common.asReSource
import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.domain.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreakingNewsViewModel @Inject constructor(
    private val newsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<GetNewsState> = MutableStateFlow(GetNewsState())
    val uiState = _uiState

    init {
        getNews()
    }

    fun getNews() {

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


}

data class GetNewsState(
    val loading: Boolean = false,
    val newsList: NewsResponse?=null
)

