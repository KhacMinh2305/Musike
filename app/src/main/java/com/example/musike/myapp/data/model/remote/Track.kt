package com.example.musike.myapp.data.model.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Track(
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("streamUrl")
    @Expose
    val streamUrl: String,

    @SerializedName("artists")
    @Expose
    val artists: List<String>,

    @SerializedName("playlists")
    @Expose
    val playlists: List<String>
)
