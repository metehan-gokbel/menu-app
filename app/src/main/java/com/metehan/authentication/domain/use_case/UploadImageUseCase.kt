package com.metehan.authentication.domain.use_case

import com.metehan.authentication.data.remote.ImageUploadResult
import com.metehan.authentication.domain.repository.ImageRepository
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(private val repository: ImageRepository) {
    suspend fun execute(base64Image: String): ImageUploadResult {
        return repository.uploadImage(base64Image)
    }
}