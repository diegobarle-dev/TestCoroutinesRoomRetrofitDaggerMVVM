package uk.co.diegobarle.core.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthInterceptor(private val keyName: String, private val parameter: String, private val type: Type) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        val request = when(type){
            Type.HEADER -> requestBuilder.addHeader(keyName, parameter).build()
            Type.QUERY_PARAMETER -> {
                val url = chain.request().url.newBuilder().addQueryParameter(keyName, parameter).build()
                requestBuilder.url(url).build()
            }
        }
        return chain.proceed(request)
    }

    enum class Type{
        HEADER, QUERY_PARAMETER
    }
}
