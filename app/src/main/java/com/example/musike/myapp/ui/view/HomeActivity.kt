package com.example.musike.myapp.ui.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.musike.myapp.ui.navigation.Discover
import com.example.musike.myapp.ui.navigation.DiscoverGraph
import com.example.musike.myapp.ui.navigation.Library
import com.example.musike.myapp.ui.navigation.LibraryGraph
import com.example.musike.myapp.ui.navigation.PlaylistDetail
import com.example.musike.myapp.ui.navigation.Setting
import com.example.musike.myapp.ui.navigation.SettingGraph
import com.example.musike.myapp.ui.navigation.SingerDetail
import com.example.musike.myapp.ui.navigation.TrackDetail
import com.example.musike.myapp.ui.navigation.navigationItems
import com.example.musike.myapp.ui.navigation.startDestinations
import com.example.musike.myapp.ui.theme.MusikeTheme
import com.example.musike.myapp.ui.theme.black
import com.example.musike.myapp.ui.theme.bottomBarIconBackgroundColor
import com.example.musike.myapp.ui.theme.seeMoreColor
import com.example.musike.myapp.ui.theme.transparent
import com.example.musike.myapp.ui.theme.white
import com.example.musike.myapp.ui.view.discover.home.component.ScreenDiscover
import com.example.musike.myapp.ui.view.discover.playlist_detail.component.ScreenPlaylistDetail
import com.example.musike.myapp.ui.view.discover.singer_detail.component.ScreenSingerDetail
import com.example.musike.myapp.ui.view.discover.track_detail.component.ScreenTrackDetail
import com.example.musike.myapp.ui.view.library.home.component.ScreenLibrary
import com.example.musike.myapp.ui.view.setting.home.component.ScreenSetting
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MusikeTheme {

                val navController = rememberNavController()
                val curDestination = navController.currentBackStackEntryAsState().value?.destination

                @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
                Scaffold(
                    bottomBar = {
                        val showBottomBar = curDestination?.hierarchy?.any { dest ->
                            startDestinations.any { dest.hasRoute(it) }
                        } == true
                        if(showBottomBar) BottomBar(navController, curDestination)
                    }
                ) {
                    NavHost(navController, startDestination = DiscoverGraph) {
                        navigation<DiscoverGraph>(startDestination = Discover) {

                            composable<Discover> {
                                ScreenDiscover(
                                    navigatePlaylistItem = { playlistId ->
                                        navController.navigate(PlaylistDetail(playlistId))
                                    }, navigateSingerItem = { singerId ->
                                        navController.navigate(SingerDetail(singerId))
                                    }, navigateTrackItem = { trackId ->
                                        navController.navigate(TrackDetail(trackId))
                                    })
                            }

                            composable<PlaylistDetail> { ScreenPlaylistDetail() }

                            composable<SingerDetail> { ScreenSingerDetail() }

                            composable<TrackDetail> {
                                ScreenTrackDetail {
                                    navController.navigateUp()
                                }
                            }

                        }

                        navigation<LibraryGraph>(startDestination = Library) {
                            composable<Library> { ScreenLibrary() }
                        }

                        navigation<SettingGraph>(startDestination = Setting) {
                            composable<Setting> { ScreenSetting() }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun BottomBar(
        navController: NavController,
        destination: NavDestination?
    ) {
        NavigationBar(
            containerColor = white,
            modifier = Modifier.height(80.dp)
        ) {
            navigationItems.forEach { item ->
                BottomBarItem(
                    item.iconRes,
                    item.label,
                    checkItemSelected(destination, item.route)
                ) {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }

    @Composable
    private fun RowScope.BottomBarItem(
        iconRes: Int,
        label: String,
        isSelected: Boolean,
        onClick: () -> Unit
    ) {
        NavigationBarItem(
            selected = isSelected,
            onClick = onClick,
            icon = {
                Icon(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            },
            label = { Text(label) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = seeMoreColor,
                selectedTextColor = seeMoreColor,
                unselectedIconColor = black,
                unselectedTextColor = black,
                indicatorColor = transparent
            ),
            modifier = Modifier
                .padding(horizontal = 10.dp, 5.dp)
                .background(
                    color = if (isSelected) bottomBarIconBackgroundColor else transparent,
                    shape = RoundedCornerShape(15.dp)
                )
        )
    }

    private inline fun <reified T : Any> checkItemSelected(
        destination: NavDestination?,
        route: T
    ): Boolean {
        return destination?.hierarchy?.let { list ->
            list.any { it.hasRoute(route::class) }
        } ?: false
    }

}