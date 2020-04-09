package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.bumptech.glide.RequestManager
import dagger.BindsInstance
import dagger.Component
import uk.co.diegobarle.core.di.CoreModule
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.viewmodel.MovieDetailsViewModel
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.viewmodel.MoviesListViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ AppModule::class, CoreModule::class ])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun applicationContext(applicationContext: Context): Builder
        @BindsInstance
        fun applicationResources(applicationResources: Resources): Builder
        fun build(): AppComponent
    }

    fun provideGlide(): RequestManager

    //ViewModels factory
    fun moviesListViewModelFactory(): ViewModelFactory<MoviesListViewModel>
    fun movieDetailsViewModelFactory(): ViewModelFactory<MovieDetailsViewModel>
}