package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage

@Database(
    entities = [Movie::class, MovieImage::class], // tables in the db
    version = 10, // schema version; whenever you change schema you have to increase the version number
    exportSchema = false // for schema version history updates
)
abstract class MovieDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDAO
    companion object{
        @Volatile
        private var instance: MovieDatabase? = null
        fun getDatabase(context: Context): MovieDatabase{
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, MovieDatabase::class.java, "movie_db")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
    }
}