package uk.co.diegobarle.core.di

import android.app.Application
import dagger.Module
import dagger.Provides
import uk.co.diegobarle.core.model.MainDatabase
import javax.inject.Singleton

/**
 * DI for database related classes
 */
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(app: Application) = MainDatabase.getInstance(app)

    @Singleton
    @Provides
    fun provideMoviesDao(db: MainDatabase) = db.moviesDao()
}