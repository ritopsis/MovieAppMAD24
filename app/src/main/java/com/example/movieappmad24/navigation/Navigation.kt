package com.example.movieappmad24.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieappmad24.screens.DetailScreen
import com.example.movieappmad24.screens.HomeScreen
import com.example.movieappmad24.screens.WatchlistScreen

@Composable
fun Navigation() {
    val navController = rememberNavController() // create a NavController instance

    NavHost(navController = navController, // pass the NavController to NavHost
        startDestination = Screen.Home.route) {  // pass a start destination

        composable(route = Screen.Home.route){   // route with name "homescreen" navigates to HomeScreen composable
            HomeScreen(navController = navController)
        }

        composable(route = Screen.Watchlist.route){   // route with name "homescreen" navigates to HomeScreen composable
            WatchlistScreen(navController = navController)
        }

        composable(
            route = "${Screen.Detail.route}/{$DETAIL_ARG_KEY}",
            arguments = listOf(navArgument(name = DETAIL_ARG_KEY) {type = NavType.StringType})
        ) { backStackEntry ->
            DetailScreen(movieId = backStackEntry.arguments?.getString(DETAIL_ARG_KEY), navController = navController)
        }
    }
}