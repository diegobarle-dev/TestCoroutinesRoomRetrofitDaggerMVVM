package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di

import android.content.Context
import com.bumptech.glide.Glide
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * DI for app related classes
 */
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideGlide(context: Context) = Glide.with(context)

}