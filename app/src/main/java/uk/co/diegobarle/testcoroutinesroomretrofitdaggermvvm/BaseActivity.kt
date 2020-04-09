package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm

import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.RequestManager
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di.injector

/**
 * Base activity to any other activities in the app to define common functionality
 */
abstract class BaseActivity : AppCompatActivity() {
    protected val glide: RequestManager by lazy {
        injector.provideGlide()
    }
}
