package com.metehan.authentication.domain.repository

import com.metehan.authentication.data.remote.ImageUploadResult

interface ImageRepository {
    suspend fun uploadImage(base64Image: String): ImageUploadResult
}