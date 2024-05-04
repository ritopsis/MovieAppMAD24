package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.interfaces.ViewModelFunctions
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class WatchlistViewModel(private val repository: MovieRepository) : ViewModel(), MoviesViewModel{
    private val _movies = MutableStateFlow(listOf<MovieWithImages>())
    override var movies = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getFavoriteMovies().distinctUntilChanged()
                .collect{ listOfMovies ->
                    _movies.value = listOfMovies
                }
        }
    }

    override fun updateFavorite(movie: Movie) {
        movie.isFavorite = !movie.isFavorite
        viewModelScope.launch {
            repository.updateMovie(movie)
        }
    }
}