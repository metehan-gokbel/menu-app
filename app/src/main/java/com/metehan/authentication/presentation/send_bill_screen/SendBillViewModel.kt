package com.metehan.authentication.presentation.send_bill_screen

import android.net.Uri
import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metehan.authentication.data.remote.ImageUploadResult
import com.metehan.authentication.domain.use_case.UploadImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject

@HiltViewModel
class SendBillViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase
) : ViewModel() {

    private val _imageUploadState = MutableStateFlow<ImageUploadState>(ImageUploadState.Initial)
    val imageUploadState: StateFlow<ImageUploadState> = _imageUploadState

    fun uploadImage(imageBase64: String) {
        viewModelScope.launch {
            _imageUploadState.value = ImageUploadState.Loading
            val result = uploadImageUseCase.execute(imageBase64)
            _imageUploadState.value = when (result) {
                is ImageUploadResult.Success -> ImageUploadState.Success
                is ImageUploadResult.Error ->{
                    ImageUploadState.Error(result.message)
                }
            }
        }
    }

}