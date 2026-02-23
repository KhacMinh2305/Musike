package com.example.musike.myapp.data.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Playlist(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String,
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("artists")
    @Expose
    val artists: List<String>
)

