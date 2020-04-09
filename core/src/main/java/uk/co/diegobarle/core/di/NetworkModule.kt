package uk.co.diegobarle.core.di

import android.content.res.Resources
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.diegobarle.core.R
import uk.co.diegobarle.core.network.AuthInterceptor
import uk.co.diegobarle.core.network.services.MovieService
import javax.inject.Singleton

/**
 * DI for API network related classes
 */
@Module
class NetworkModule: CoreNetworkModule() {

    @MoviesApi
    @Provides
    fun provideMoviesPrivateOkHttpClient(
        upstreamClient: OkHttpClient,
        resources: Resources
    ): OkHttpClient {
        return upstreamClient.newBuilder()
            .addInterceptor(AuthInterceptor(resources.getString(R.string.base_api_header_key), resources.getString(R.string.base_api_key_value), AuthInterceptor.Type.QUERY_PARAMETER))
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieService(@MoviesApi okhttpClient: OkHttpClient,
                           converterFactory: GsonConverterFactory,
                           resources: Resources
    ) = provideService(
        resources.getString(R.string.base_api_url),
        okhttpClient,
        converterFactory,
        null,
        MovieService::class.java)
}