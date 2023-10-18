package com.muzo.newsapp.feature.fragment.loginFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzo.newsapp.core.data.remote.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginFragmentViewModel @Inject constructor(private val repository: AuthRepository) :
    ViewModel() {

    val loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())


    suspend fun register(email: String, password: String) {
        repository.register(email, password)
    }

    suspend fun login(email: String, password: String, onSuccess: () -> Unit, onError: (Exception) -> Unit) {
        viewModelScope.launch {
            loginState.value = loginState.value.copy(loading = true)
            try {
                repository.login(email, password)
                loginState.value = loginState.value.copy(loading = false)
                onSuccess()
            } catch (e: Exception) {
                loginState.value = LoginState()
                onError(e)
            }
        }
    }

    suspend fun logout() {
        repository.logout()
    }
}

data class LoginState(
    val loading: Boolean = false,
)