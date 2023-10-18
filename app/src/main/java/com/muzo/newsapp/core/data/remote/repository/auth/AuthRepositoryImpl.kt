package com.muzo.newsapp.core.data.remote.repository.auth

import com.muzo.newsapp.core.data.remote.source.auth.AuthDataSource
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(private val dataSource: AuthDataSource) :
    AuthRepository {

    override suspend fun register(email: String, password: String) {
        dataSource.register(email, password)
    }

    override suspend fun login(email: String, password: String) {
        dataSource.login(email, password)
    }

    override suspend fun logout() {
        dataSource.logout()
    }
}
