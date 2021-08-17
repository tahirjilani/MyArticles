package com.tahir.nyt.core.domain

sealed class Resource<out R> {

    data class Loading<out T>(val message: T? = null) : Resource<T>()
    data class Success<out T>(val data: T, val message: String? = null) : Resource<T>()
    data class Error(val exception: Exception) : Resource<Nothing>()
}