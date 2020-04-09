package uk.co.diegobarle.core.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uk.co.diegobarle.core.model.dao.MoviesDao
import uk.co.diegobarle.core.model.entity.IdList
import uk.co.diegobarle.core.model.entity.Movie

@Database(
    entities = [
        Movie::class
    ],
    version = 1,
    exportSchema = false)
@TypeConverters(
    IdList.Converter::class
)
abstract class MainDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {

        const val DATABASE_NAME = "main_db"

        @Volatile
        private var instance: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MainDatabase {
            return Room
                .databaseBuilder(context, MainDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}