package com.muzo.newsapp.feature.fragment.userFragment

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.core.data.local.source.LocalNewsDataSource
import com.muzo.newsapp.core.data.remote.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class FragmentUserViewModel @Inject constructor(
    private val repository: AuthRepository,
    private val localNewsDataSource: LocalNewsDataSource
) : ViewModel() {



    val currentUser: FirebaseUser?
        get() = repository.currentUser



    fun logOut() {
        repository.logout()
    }

    suspend fun deleteAllSavedNews() {
        localNewsDataSource.deleteAllSavedNews()
    }

}