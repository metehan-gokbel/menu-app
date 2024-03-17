package com.metehan.authentication.presentation.send_bill_screen

import android.content.Context
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CreateImageFile {
    fun execute(context: Context): File {
        val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH:mm:ss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_$timeStamp"
        val file = File.createTempFile(imageFileName, ".jpg", context.externalCacheDir)
        return file.absoluteFile
    }
}