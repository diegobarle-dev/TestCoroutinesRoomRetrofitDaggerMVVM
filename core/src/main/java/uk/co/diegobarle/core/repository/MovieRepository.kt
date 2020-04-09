package uk.co.diegobarle.core.repository

import uk.co.diegobarle.core.datamanager.DataManager
import uk.co.diegobarle.core.datamanager.RequestLevel
import uk.co.diegobarle.core.mappers.MovieMapper
import uk.co.diegobarle.core.model.dao.MoviesDao
import uk.co.diegobarle.core.network.servermodel.movie.SMovie
import uk.co.diegobarle.core.network.services.MovieService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class MovieRepository @Inject constructor() {

    @Inject
    protected lateinit var dataManager: DataManager

    @Inject
    protected lateinit var movieService: MovieService

    @Inject
    protected lateinit var moviesDao: MoviesDao

    @Inject
    protected lateinit var movieMapper: MovieMapper

    fun fetchMostPopularMovies(requestLevel: RequestLevel) = dataManager.resultLiveData(
        requestLevel,
        {
            moviesDao.getMostPopularMovies()
        },
        {
            movieService.fetchMostPopularMovies()
        },
        { movies ->
            moviesDao.insert(movies)
        },
        { server: List<SMovie> ->
            server.map { movieMapper.fromServer(it) }
        }
    )

    fun fetchMovies(searchQuery: String, requestLevel: RequestLevel) = dataManager.resultLiveData(
        requestLevel,
        {
            moviesDao.getMoviesContaining(searchQuery)
        },
        {
            movieService.fetchMovies(searchQuery)
        },
        { movies ->
            moviesDao.insert(movies)
        },
        { server: List<SMovie> ->
            server.map { movieMapper.fromServer(it) }
        }
    )

    fun getMovie(movieId: Int, requestLevel: RequestLevel) = dataManager.resultLiveData(
        requestLevel,
        {
            moviesDao.getMovie(movieId)
        },
        {
            TODO()
        },
        { movie ->
            moviesDao.insert(movie)
        },
        { server: SMovie ->
            TODO()
        }
    )
}