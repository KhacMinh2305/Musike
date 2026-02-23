package com.example.musike.myapp.data.repository.singer

import com.example.musike.myapp.data.model.remote.Singer
import com.example.musike.myapp.data.model.usage.DataState
import com.example.musike.myapp.data.source.singer.remote.RemoteSingerDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SingerRepositoryImpl(
    private val remoteDataSource: RemoteSingerDataSource
): SingerRepository {

    private val _singerState = MutableStateFlow(DataState<List<Singer>>())
    val singerState = _singerState.asStateFlow()

    override suspend fun getSingers(): StateFlow<DataState<List<Singer>>> {
        if (_singerState.value.loaded) return singerState
        _singerState.value = try {
            DataState(remoteDataSource.getSinger(), null, true)
        } catch (e: Exception) {
            DataState(null, e.message, true)
        }
        return singerState
    }
}