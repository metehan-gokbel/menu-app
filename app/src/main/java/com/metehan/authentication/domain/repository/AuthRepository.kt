package com.metehan.authentication.domain.repository

import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.common.api.Response
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.metehan.authentication.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow


interface AuthRepository {
    val currentUser: FirebaseUser?
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(name: String, email: String, password: String): Flow<Resource<AuthResult>>
    fun sendEmailVerification(): Flow<Resource<Boolean>>
    suspend fun reloadFirebaseUser(): Flow<Resource<Boolean>>
    fun signOut()
    fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean>
    suspend fun sendPasswordResetEmail(email: String): Flow<Resource<Boolean>>
    suspend fun oneTapSignInWithGoogle(): Flow<Resource<BeginSignInResult>>

    suspend fun firebaseSignInWithGoogle(googleCredential: AuthCredential): Flow<Resource<Boolean>>
}