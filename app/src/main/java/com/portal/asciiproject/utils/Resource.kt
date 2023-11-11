package com.portal.asciiproject.utils

data class Resource<out T>(val status: AsciiStatus, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(AsciiStatus.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(AsciiStatus.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(AsciiStatus.LOADING, data, null)
        }

    }

}