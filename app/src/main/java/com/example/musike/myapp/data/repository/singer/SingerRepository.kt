package com.example.musike.myapp.data.repository.singer

import com.example.musike.myapp.data.model.remote.Singer
import com.example.musike.myapp.data.model.usage.DataState
import kotlinx.coroutines.flow.StateFlow

interface SingerRepository {

    suspend fun getSingers(): StateFlow<DataState<List<Singer>>>

    suspend fun reloadSingers()

}