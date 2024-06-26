package com.example.movieappmad24.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.worker.WorkManagerDatabaseRepository

@Database(
    entities = [Movie::class, MovieImage::class], // tables in the db
    version = 4, // schema version; whenever you change schema you have to increase the version number
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
                    .addCallback(callback = seedDatabase(context = context))
                    .build()
                    .also {
                        instance = it
                    }
            }
        }
        private fun seedDatabase(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {//works when you uninstall the app. Prof: if you already have a DB instance running you might need to delete your app first...
                    super.onCreate(db)
                    WorkManagerDatabaseRepository(context).seedDatabase()
                }
            }
        }
    }
}
