package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.functions.MovieList
import com.example.movieappmad24.functions.SimpleBottomAppBar
import com.example.movieappmad24.functions.SimpleTopAppBar
import com.example.movieappmad24.models.getMovies

@Composable
fun WatchlistScreen(navController: NavController) {
    Scaffold (
        topBar = { SimpleTopAppBar(title = "Your Watchlist", navController = navController)},
        bottomBar = { SimpleBottomAppBar(navController = navController) }
    ){ innerPadding ->
        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = getMovies(), //The list of movies can be hardcoded for now
            navController = navController
        )
    }
}