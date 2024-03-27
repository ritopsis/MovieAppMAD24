package com.example.movieappmad24.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movieappmad24.models.getMovieById
import com.example.movieappmad24.functions.MovieRow
import com.example.movieappmad24.models.Movie

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(movieId: String?, navController: NavController) {
    Text(text = movieId + "")
    val movie = getMovieById(movieId)
    if(movie != null)
    {
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(movie.title)},
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack()}) {
                            Icon(imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "ArrowBack" )
                        }}
                )
            },
        ){ innerPadding ->
            DetailContent(
                modifier = Modifier.padding(innerPadding),
                movie = movie
            )
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier,
    movie: Movie,
    ){
    Column(modifier = modifier) {
        MovieRow(
            movie = movie,
            onItemClick = {}
        )
        LazyRow(
            modifier = Modifier
                .height(250.dp)
        ){
            items(movie.images) { image ->
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                ){
                    AsyncImage(
                        model = image,
                        contentDescription = "movie poster",
                        modifier = Modifier
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
            }
        }
    }
}