package com.metehan.authentication.data.remote

import com.metehan.authentication.data.remote.dto.MerchantDto
import com.metehan.authentication.data.remote.dto.ProductDto
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface MenuAPI {
    @GET("/api/v0.1.0/merchant/")
    suspend fun getMerchantList() : List<MerchantDto>

    @GET("/api/v0.1.0/merchant/{merchantId}/product/")
    suspend fun getProduct(
        @Path("merchantId") id: String
    ): List<ProductDto>

    @Multipart
    @POST("/api/v0.1.0/upload/")
    suspend fun uploadImage(
        @Part image: MultipartBody.Part
    ): Response<Unit>
}