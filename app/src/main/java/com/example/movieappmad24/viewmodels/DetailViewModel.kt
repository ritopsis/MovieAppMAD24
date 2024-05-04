package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.interfaces.ViewModelFunctions
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieWithImages
import com.example.movieappmad24.models.getMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: MovieRepository) : ViewModel(), ViewModelFunctions {
    private val _movie = MutableStateFlow<MovieWithImages?>(null)
    val movie: StateFlow<MovieWithImages?> = _movie.asStateFlow()

    fun getMovieById(movieId: String) {
        val id: Long? = movieId.toLongOrNull()
        if (id != null) {
            viewModelScope.launch {
                repository.getById(id).collect { movie ->
                    _movie.value = movie
                }
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