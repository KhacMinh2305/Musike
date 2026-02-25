package com.example.musike.myapp.ui.navigation

data class NavigationItem<T> (
    val route: T,
    val iconRes: Int,
    val label: String
)

val navigationItems = listOf(
    NavigationItem(DiscoverGraph, com.example.musike.R.drawable.ic_home, "Discover"),
    NavigationItem(LibraryGraph, com.example.musike.R.drawable.ic_library, "Library"),
    NavigationItem(SettingGraph, com.example.musike.R.drawable.ic_setting, "Setting")
)