package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm

import android.app.Application
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di.AppComponent
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di.DaggerAppComponent
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di.DaggerComponentProvider

class MoviesApp : Application(), DaggerComponentProvider {

    override val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.builder()
            .application(this)
            .applicationContext(applicationContext)
            .applicationResources(resources)
            .build()
    }

}