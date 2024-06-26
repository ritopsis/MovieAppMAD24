package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.data.MovieRepository

class MoviesViewModelFactory(private val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            return HomeViewModel(repository = repository) as T
        }
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(repository = repository) as T
        }
        if(modelClass.isAssignableFrom(WatchlistViewModel::class.java)){
            return WatchlistViewModel(repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}