package com.example.movieappmad24.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
    ){
    data object Home: BottomNavigationItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screen.Home.route
    )
    data object Watchlist: BottomNavigationItem(
        label = "Watchlist",
        icon = Icons.Default.Star,
        route = Screen.Watchlist.route
    )
}
fun getBottomNavigationBarItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Watchlist
    )
}
