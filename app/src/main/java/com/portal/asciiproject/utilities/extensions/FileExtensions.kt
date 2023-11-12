package com.portal.asciiproject.utilities.extensions

import android.webkit.MimeTypeMap
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import java.io.File
import java.net.URLEncoder

fun File.getExtension(): MediaType? {
    var type: String? = null
    val encoded: String = try {
        URLEncoder.encode(name, "UTF-8").replace("+", "%20")
    } catch (e: Exception) {
        name
    }
    val extension = MimeTypeMap.getFileExtensionFromUrl(encoded)
    if (extension != null) {
        type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
    }

    return type?.toMediaType()
}