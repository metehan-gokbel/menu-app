package com.metehan.authentication.data.repository

import android.util.Base64
import com.metehan.authentication.data.remote.ImageUploadResult
import com.metehan.authentication.data.remote.MenuAPI
import com.metehan.authentication.domain.repository.ImageRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(private val api: MenuAPI) : ImageRepository {
    override suspend fun uploadImage(base64Image: String): ImageUploadResult {
        return try {
            val imagePart = createPartFromString(base64Image)
            val response = api.uploadImage(imagePart)
            if (response.isSuccessful) {
                ImageUploadResult.Success
            } else {
                ImageUploadResult.Error("Error uploading image: ${response.code()}")
            }
        } catch (e: Exception) {
            ImageUploadResult.Error("An error occurred: ${e.message}")
        }
    }

    private fun createPartFromString(base64Image: String): MultipartBody.Part {
        val requestBody = RequestBody.create("image/*".toMediaTypeOrNull(), Base64.decode(base64Image, Base64.DEFAULT))
        return MultipartBody.Part.createFormData("file", "image.jpg", requestBody)
    }
}