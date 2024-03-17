package com.metehan.authentication.data.remote

import com.metehan.authentication.domain.repository.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val tokenProvider: TokenProvider) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("X-Firebase-AppCheck", tokenProvider.getToken())
            .build()
        return chain.proceed(request)
    }
}
