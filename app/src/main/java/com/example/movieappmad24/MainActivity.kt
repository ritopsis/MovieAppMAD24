package com.example.movieappmad24

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                MovieApp()
            }
        }
    }
}

@Composable
fun MovieApp()
{
    Scaffold(
        containerColor = Color.Gray,
        topBar = { MovieAppTopBar(text = "Movie App") },
        bottomBar = { MovieAppBottomBar()},
    ) { innerPadding ->
        MovieList(
            movies = getMovies(),
            // considering inner padding of Scaffold to display movie list
            modifier = Modifier.padding(innerPadding)
        )
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppTopBar(text: String) {
    CenterAlignedTopAppBar(
        title = {
            Text(text = text, maxLines = 1 )
        },
    )
    //https://developer.android.com/jetpack/compose/components/app-bars
}

@Composable
fun MovieAppBottomBar() {
    BottomAppBar{
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                )
            },
            label = {
                Text(text = "Home")
            },
            selected = false, // ToDo later exercise
            onClick = { /* ToDo later */ }        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Watchlist"
                )
            },
            label = {
                Text(text = "Watchlist")
            },
            selected = false, // ToDo later exercise
            onClick = { /* ToDo later */ }
        )
    }
}

@Composable
fun MovieList(movies: List<Movie> = getMovies(), modifier: Modifier){
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            MovieRow(movie = movie)
        }
    }
}


@Composable
fun MovieRow(movie: Movie){
    var showDetails by remember {
        mutableStateOf(false)
    }
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .animateContentSize(), // change height of card expand/shrink
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = movie.images.firstOrNull(),
                    placeholder = painterResource(id = R.drawable.movie_image),
                    contentDescription = "movie image",
                    contentScale = ContentScale.Crop,
                    //https://coil-kt.github.io/coil/compose/
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colorScheme.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movie.title)
                Icon(modifier = Modifier
                    .clickable {
                        showDetails = !showDetails
                    },
                    imageVector =
                    if (showDetails) Icons.Filled.KeyboardArrowDown
                    else Icons.Default.KeyboardArrowUp, contentDescription = "show more")
            }
            val textStyle = TextStyle(fontSize = 14.sp)
            AnimatedVisibility(visible = showDetails)
            {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(text = "Director: ${movie.director}", style = textStyle )
                    Text(text = "Released: ${movie.year}", style = textStyle)
                    Text(text = "Genre: ${movie.genre}", style = textStyle)
                    Text(text = "Actors: ${movie.actors}", style = textStyle)
                    Text(text = "Rating: ${movie.rating}", style = textStyle)
                    Divider()
                    Text(text = "Plot: ${movie.plot}", style = textStyle)
                }
            }
            //https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedvisibility
        }
    }
}


@Preview
@Composable
fun DefaultPreview(){
    MovieAppMAD24Theme {
        MovieApp()
    }
}