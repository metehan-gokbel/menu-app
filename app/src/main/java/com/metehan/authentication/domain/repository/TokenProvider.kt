package com.metehan.authentication.domain.repository

interface TokenProvider {
    fun getToken(): String
}