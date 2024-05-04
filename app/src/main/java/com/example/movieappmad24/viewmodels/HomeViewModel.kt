package com.example.movieappmad24.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.interfaces.ViewModelFunctions
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.models.getMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel(), MoviesViewModel{
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
        }
    }

    fun addMovies()
    {
        var nint: Long = 1
        val movies = getMovies()
        movies.forEach{
            viewModelScope.launch {
                repository.addMovie(it)
            }
            it.images.forEach{url ->
                viewModelScope.launch {
                    repository.addMovieImages(MovieImage(movieDbId = nint, url = url))
                }
            }
            nint += 1
        }

    }
}