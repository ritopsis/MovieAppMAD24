package com.example.movieappmad24.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.movieappmad24.functions.MovieList
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WatchlistScreen(navController: NavController) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Movie App") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    label = { Text("Home") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Home.route) },
                    icon = { Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Go to home"
                    )
                    }
                )
                NavigationBarItem(
                    label = { Text("Watchlist") },
                    selected = true,
                    onClick = {navController.navigate(Screen.Watchlist.route)},
                    icon = { Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "Go to watchlist"
                    )
                    }
                )
            }
        }
    ){ innerPadding ->
        MovieList(
            modifier = Modifier.padding(innerPadding),
            movies = getMovies(),
            navController = navController
        )
    }
}