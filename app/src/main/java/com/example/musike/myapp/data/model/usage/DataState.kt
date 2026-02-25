package com.example.musike.myapp.data.model.usage

data class DataState<T>(
    val mData: T? = null,
    val error: String? = null,
    var loaded: Boolean = false
)
