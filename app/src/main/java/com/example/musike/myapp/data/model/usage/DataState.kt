package com.example.musike.myapp.data.model.usage

data class DataState<T>(
    val mData: T? = null,
    val error: String? = null,
    val loaded: Boolean = false
)
