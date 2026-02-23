package com.example.musike.myapp.data.model.usage

sealed class UiState<out T> {

    object Loading : UiState<Nothing>()

    data class Success<out T>(val mData: T) : UiState<T>()

    data class Error(val message: String) : UiState<Nothing>()
}