package com.example.musike.myapp.data.model.remote
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Singer(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("imageUrl")
    @Expose
    val imageUrl: String,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("playlists")
    @Expose
    val playlists: List<String>
)
