package com.muzo.newsapp.feature.fragment.detailFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.core.common.asReSource
import com.muzo.newsapp.core.data.local.source.LocalNewsDataSource
import com.muzo.newsapp.core.data.model.Article
import com.muzo.newsapp.domain.GetNewsFromRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val localDataSource: LocalNewsDataSource, private val getNewsFromRoom: GetNewsFromRoom
) : ViewModel() {

    val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState = _uiState

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {

            getNewsFromRoom().asReSource().onEach { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.value = _uiState.value.copy(loading = true)
                    }

                    is Resource.Error -> {
                        _uiState.value = _uiState.value.copy(loading = false)
                    }

                    is Resource.Success -> {
                        _uiState.value =
                            _uiState.value.copy(loading = false, newsLocalData = result.data)
                    }
                }
            }

        }
    }


    suspend fun saveNews(newsList: List<Article>) {
        localDataSource.insertNews(newsList)
    }

    suspend fun deleteNews(newsId: Int) {
        localDataSource.deleteNewsByUid(newsId)
    }

}

data class HomeUiState(
    val loading: Boolean = false, val newsLocalData: List<Article>? = null
)