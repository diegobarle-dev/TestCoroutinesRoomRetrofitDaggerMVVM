package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.di

import android.app.Activity
import androidx.fragment.app.Fragment

interface DaggerComponentProvider {
    val appComponent: AppComponent
}

val Activity.injector get() = (application as DaggerComponentProvider).appComponent

val Fragment.injector get() = (activity?.application as? DaggerComponentProvider)?.appComponent