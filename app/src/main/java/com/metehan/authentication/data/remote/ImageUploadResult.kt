package com.metehan.authentication.data.remote

sealed class ImageUploadResult {
    object Success : ImageUploadResult()
    data class Error(val message: String) : ImageUploadResult()
}