package com.muzo.newsapp.core.data.remote.source.auth

interface AuthDataSource {
    suspend fun register(email: String, password: String)
    suspend fun login(email: String, password: String)
    suspend fun logout()
}

