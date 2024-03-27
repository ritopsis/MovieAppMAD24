package com.example.movieappmad24.navigation

const val DETAIL_ARG_KEY = "movieId"
sealed class Screen (val route: String) {
    data object Home: Screen(route = "homescreen")
    data object Watchlist: Screen(route = "watchlistscreen")
    data object Detail: Screen(route = "detailscreen")

}