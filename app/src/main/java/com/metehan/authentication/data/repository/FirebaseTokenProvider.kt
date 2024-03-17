package com.metehan.authentication.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.metehan.authentication.domain.repository.TokenProvider
import javax.inject.Inject

class FirebaseTokenProvider @Inject constructor(private val firebaseUser: FirebaseUser) : TokenProvider {
    override fun getToken(): String {
        println("token: ${firebaseUser.getIdToken(false).result?.token}")
        return firebaseUser.getIdToken(false).result?.token ?: ""
    }
}
