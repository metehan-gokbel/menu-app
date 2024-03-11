package com.metehan.authentication.data.repository

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.UserProfileChangeRequest
import com.metehan.authentication.domain.repository.AuthRepository
import com.metehan.authentication.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthRepository {

    override val currentUser get() = firebaseAuth.currentUser

    override fun loginUser(email: String, password: String): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Resource.Success(data = result))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }

    override fun registerUser(
        name: String,
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> {
        return flow {
            emit(Resource.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())
            emit(Resource.Success(data = result))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }

    override fun sendEmailVerification(): Flow<Resource<Boolean>> {
        return flow{
            emit(Resource.Loading())
            firebaseAuth.currentUser?.sendEmailVerification()?.await()
            emit(Resource.Success(data = true))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }

    override suspend fun reloadFirebaseUser(): Flow<Resource<Boolean>> {
        return flow{
            emit(Resource.Loading())
            firebaseAuth.currentUser?.reload()?.await()
            emit(Resource.Success(data = true))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }

    override fun signOut() {
        firebaseAuth.signOut()
    }

    override fun getAuthState(viewModelScope: CoroutineScope): StateFlow<Boolean> = callbackFlow {
        val authStateListener = AuthStateListener { auth ->
            trySend(auth.currentUser == null)
        }
        firebaseAuth.addAuthStateListener(authStateListener)
        awaitClose {
            firebaseAuth.removeAuthStateListener(authStateListener)
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), firebaseAuth.currentUser == null)

    override suspend fun sendPasswordResetEmail(email: String): Flow<Resource<Boolean>> {
        return flow{
            emit(Resource.Loading())
            firebaseAuth.sendPasswordResetEmail(email).await()
            emit(Resource.Success(data = true))
        }.catch {
            emit(Resource.Error(message = it.message.toString()))
        }
    }
}