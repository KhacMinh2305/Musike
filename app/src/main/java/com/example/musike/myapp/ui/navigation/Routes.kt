package com.example.musike.myapp.ui.navigation

import kotlinx.serialization.Serializable

/*----------------Discover Graph----------------*/
@Serializable
data object DiscoverGraph

@Serializable
data object Discover

@Serializable
data class PlaylistDetail(val id: String)

@Serializable
data class SingerDetail(val id: String)

@Serializable
data class TrackDetail(val id: String)

/*----------------Library Graph----------------*/
@Serializable
data object LibraryGraph

@Serializable
data object Library

@Serializable
data object Download

@Serializable
data object Import

/*----------------Setting Graph----------------*/
@Serializable
data object SettingGraph

@Serializable
data object Setting

@Serializable
data object Profile


val startDestinations = listOf(
    Discover::class,
    Library::class,
    Setting::class
)


