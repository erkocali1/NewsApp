package com.muzo.newsapp.feature.fragment.infoFragment

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import com.muzo.newsapp.core.common.Resource
import com.muzo.newsapp.core.data.remote.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


@HiltViewModel
class InfoFragmentViewModel @Inject constructor(private val repository: AuthRepository) :
    ViewModel() {

    private val _userInfoState = MutableStateFlow<Resource<FirebaseUser>?>(null)
    val userInfo: StateFlow<Resource<FirebaseUser>?> = _userInfoState

    init {
        if (repository.currentUser != null) {
            _userInfoState.value = Resource.Success(repository.currentUser!!)
        }
    }


}