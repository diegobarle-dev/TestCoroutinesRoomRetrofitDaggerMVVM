package uk.co.diegobarle.core.di

import dagger.Module
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * DI for basic network related generation classes
 */
@Module
abstract class CoreNetworkModule {

    protected fun <T> provideService(serviceEndpoint: String,
                                     okhttpClient: OkHttpClient,
                                     converterFactory: GsonConverterFactory,
                                     adapterFactory: CallAdapter.Factory?,
                                     clazz: Class<T>): T {
        return createRetrofit(serviceEndpoint, okhttpClient, converterFactory, adapterFactory).create(clazz)
    }

    private fun createRetrofit(
        serviceEndpoint: String,
        okhttpClient: OkHttpClient,
        converterFactory: Converter.Factory,
        adapterFactory: CallAdapter.Factory?
    ): Retrofit {
        val retrofit = Retrofit.Builder()
            .baseUrl(serviceEndpoint)
            .client(okhttpClient)
            .addConverterFactory(converterFactory)
        adapterFactory?.let { retrofit.addCallAdapterFactory(adapterFactory) }
        return retrofit.build()
    }
}