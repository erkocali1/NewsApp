package com.muzo.newsapp.core.data.remote.repository.auth

import com.google.firebase.auth.FirebaseUser
import com.muzo.newsapp.core.common.Resource

interface AuthRepository {

    val currentUser:FirebaseUser?
    suspend fun register(name:String,email: String, password: String):Resource<FirebaseUser>
    suspend fun login(email: String, password: String):Resource<FirebaseUser>
    fun logout()
}
