package com.metehan.authentication.presentation.send_bill_screen

sealed class ImageUploadState {
    data object Initial : ImageUploadState()
    data object Loading : ImageUploadState()
    data object Success : ImageUploadState()
    data class Error(val message: String) : ImageUploadState()
}