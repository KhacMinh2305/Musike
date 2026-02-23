package com.example.musike.myapp.data.source.singer.remote

import com.example.musike.myapp.data.model.remote.Singer

interface RemoteSingerDataSource {

    suspend fun getSinger(): List<Singer>
}