package com.ekotech.poketech.util

//Based off :
// https://github.com/icanerdogan/ValorantGuideApp/blob/master/app/src/main/java/com/ibrahimcanerdogan/valorantguideapp/util/Resource.kt
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}