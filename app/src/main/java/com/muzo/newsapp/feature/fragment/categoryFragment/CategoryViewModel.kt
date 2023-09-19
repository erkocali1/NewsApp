package com.muzo.newsapp.feature.fragment.categoryFragment

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.core.common.asReSource
import com.muzo.newsapp.core.data.model.NewsResponse
import com.muzo.newsapp.domain.CategoryResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val categoryResultUseCase: CategoryResultUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {


    private val _uiState: MutableStateFlow<GetNewsStateCategory> =
        MutableStateFlow(GetNewsStateCategory())
    val uiState = _uiState

    init {
        val sharedPreferences = context.getSharedPreferences("file", AppCompatActivity.MODE_PRIVATE)
        val category = sharedPreferences.getString("category", "")
        getCategoryNews(category!!)
    }

    private fun getCategoryNews(category: String) {
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
}

data class GetNewsStateCategory(
    val loading: Boolean = false,
    val categoryList: NewsResponse? = null
)