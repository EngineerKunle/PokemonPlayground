package com.ekotech.poketech.uistate.data

import androidx.annotation.StringRes

sealed class UIResult<out T : Any> {
    data object Loading : UIResult<Nothing>()
    data class Success<out T : Any>(val data: T) : UIResult<T>()
    data class Error(
        val exception: Exception,
        @StringRes val errorMessage: Int,
    ) : UIResult<Nothing>()
}