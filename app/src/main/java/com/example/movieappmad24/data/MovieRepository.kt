package com.example.movieappmad24.data

import androidx.room.Dao
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.Flow
import okio.AsyncTimeout.Companion.lock

class MovieRepository(private val movieDao: MovieDAO) {

    suspend fun addMovie(movie: Movie) = movieDao.add(movie)

    suspend fun updateMovie(movie: Movie) = movieDao.update(movie)

    suspend fun deleteMovie(movie: Movie) = movieDao.delete(movie)

    fun getAllMovies(): Flow<List<Movie>> = movieDao.getAll()

    fun getFavoriteMovies(): Flow<List<Movie>> = movieDao.getFavorites()

    fun getById(id: Long): Flow<Movie?> = movieDao.get(id)

    companion object {
        @Volatile
        private var Instance: MovieRepository? = null

        fun getInstance(dao: MovieDAO) = Instance ?: synchronized(lock = this) {
            Instance ?: MovieRepository(dao).also { Instance = it }
        }
    }
}