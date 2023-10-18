package com.muzo.newsapp.core.data.remote.source.auth

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(private val firebaseAuth: FirebaseAuth):
    AuthDataSource {



    override suspend fun register(email: String, password: String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { /* işlem sonucunu ele al */ }
    }

    override suspend fun login(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { /* işlem sonucunu ele al */ }
    }

    override suspend fun logout() {
        firebaseAuth.signOut()
    }
}