package app.snapcart.cashier.utils

import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.content.FileProvider
import app.snapcart.cashier.BuildConfig
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object ComposeFileProvider {

    fun createImageFile(context: Context): Uri {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.ENGLISH).format(Date())
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val file = File.createTempFile(
            "JPEG_${timeStamp}_", // prefix
            ".jpg", // suffix
            storageDir // directory
        )
        return FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.application_authority",
            file
        )
    }
}
