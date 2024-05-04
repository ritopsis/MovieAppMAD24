package com.example.movieappmad24.data

import DatabaseWorker
import android.content.Context
import android.os.Debug
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.WorkManager
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieImage
import com.example.movieappmad24.worker.WorkManagerDatabaseRepository

@Database(
    entities = [Movie::class, MovieImage::class], // tables in the db
    version = 2, // schema version; whenever you change schema you have to increase the version number
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
                        Log.i("database", "seeded?")
                    }
            }
        }
        private fun seedDatabase(context: Context): Callback {
            return object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {//works when you uninstall the app. Prof: if you already have a DB instance running you might need to delete your app first...
                    super.onCreate(db)
                    Log.i("Database", "onCreate called")
                    WorkManagerDatabaseRepository(context).seedDatabase()
                }
            }
        }
    }
}
