package com.example.movieappmad24.functions

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieappmad24.navigation.getBottomNavigationBarItems

@Composable
fun BackButton(navController: NavController) {
    IconButton(onClick = { navController.popBackStack()}) {
        Icon(imageVector = Icons.Filled.ArrowBack,
            contentDescription = "ArrowBack" )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopAppBar(title: String = "", backbutton: Boolean = false, navController: NavController) {
        CenterAlignedTopAppBar(
            title = { Text(title)},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            ),
            navigationIcon = {
                if (backbutton)
                {
                    BackButton(navController = navController)
                }
            }
        )
}
@Composable
fun SimpleBottomAppBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar{
        getBottomNavigationBarItems().forEach{ item ->
            NavigationBarItem(
                label = { Text(item.label) },
                selected = currentDestination?.hierarchy?.any{
                    it.route == item.route
                } == true,
                onClick = {navController.navigate(item.route){
                    popUpTo(item.route){
                        inclusive = true
                    }
                } },
                icon = { Icon(
                    imageVector = item.icon,
                    contentDescription = item.label
                )
                }
            )
        }
    }
}