package com.muzo.newsapp.feature.fragment.searchNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.core.common.asReSource
import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.domain.SearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(private val searchNewsUseCase: SearchResultUseCase) :
    ViewModel() {

    val _uiState: MutableStateFlow<GetSearchNewsState> = MutableStateFlow(GetSearchNewsState())
    val uiState = _uiState

     fun getSearchNews(search:String,page: Int) {

        viewModelScope.launch {
            searchNewsUseCase(search,page).asReSource().onEach { result ->

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

data class GetSearchNewsState(
    val loading: Boolean = false,
    val newsList: NewsResponse? = null,
)