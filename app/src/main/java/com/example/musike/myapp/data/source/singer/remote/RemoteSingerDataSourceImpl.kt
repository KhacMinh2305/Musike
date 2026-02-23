package com.example.musike.myapp.data.source.singer.remote

import android.content.Context
import com.example.musike.myapp.data.config.ApiService
import com.example.musike.myapp.data.model.remote.Singer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.collections.emptyList

class RemoteSingerDataSourceImpl(
    private val context: Context,
    private val apiService: ApiService
): RemoteSingerDataSource {

    override suspend fun getSinger(): List<Singer> = withContext(Dispatchers.IO) {
        val response = apiService.getSingers()
        if(!response.isSuccessful) throw Exception(response.errorBody()?.toString())
        return@withContext response.body() ?: emptyList()
    }

}