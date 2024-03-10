package com.metehan.authentication.domain.repository

import com.google.firebase.auth.AuthResult
import com.metehan.authentication.util.Resource
import kotlinx.coroutines.flow.Flow


interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(name: String, email: String, password: String): Flow<Resource<AuthResult>>
}