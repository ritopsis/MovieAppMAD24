package com.example.movieappmad24.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.interfaces.MoviesViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel(), MoviesViewModel {
    private val _movies = MutableStateFlow(listOf<MovieWithImages>())
    override var movies = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMovies().distinctUntilChanged()
                .collect{ listOfMovies ->
                    _movies.value = listOfMovies
                    Log.i("test", "Getting movies")
                }
        }
    }

    override fun updateFavorite(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        viewModelScope.launch {
            repository.updateMovie(movie)
            Log.i("updated", "UPDTAED")
        }
    }

}