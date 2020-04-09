package uk.co.diegobarle.core.network.services

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.diegobarle.core.network.servermodel.SResponse
import uk.co.diegobarle.core.network.servermodel.movie.SMovie

interface MovieService {

    @GET("3/search/movie")
    suspend fun fetchMovies(@Query("query") query: String): Response<SResponse<List<SMovie>>>

    @GET("3/movie/top_rated")
    suspend fun fetchMostPopularMovies(): Response<SResponse<List<SMovie>>>
}