package com.muzo.newsapp.core.data.remote.repository.auth

interface AuthRepository {
    suspend fun register(email: String, password: String)
    suspend fun login(email: String, password: String)
    suspend fun logout()
}
