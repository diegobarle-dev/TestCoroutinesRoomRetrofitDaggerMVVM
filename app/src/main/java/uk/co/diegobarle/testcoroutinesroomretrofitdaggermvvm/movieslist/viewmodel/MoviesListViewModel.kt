package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import uk.co.diegobarle.core.datamanager.DataResult
import uk.co.diegobarle.core.datamanager.RequestLevel
import uk.co.diegobarle.core.datamanager.Status
import uk.co.diegobarle.core.repository.MovieRepository
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.movieslist.model.VMovieMapper
import javax.inject.Inject

class MoviesListViewModel @Inject constructor(
    private val moviesRepository: MovieRepository,
    private val vMovieMapper: VMovieMapper
) : ViewModel(){

    var page = 1

    fun fetchMovies(query: String) =
        moviesRepository.fetchMovies(query, RequestLevel.FULL_STACK).map {
            when(it.status){
                Status.SUCCESS -> DataResult.success(vMovieMapper.map(it.data!!))
                Status.ERROR -> DataResult.error(it.error!!, it.data?.let{ vMovieMapper.map(it) })
                Status.LOADING -> DataResult.loading(it.data?.let{ vMovieMapper.map(it) })
            }
        }

    fun fetchPopularMovies() =
        moviesRepository.fetchMostPopularMovies(RequestLevel.FULL_STACK).map {
            when(it.status){
                Status.SUCCESS -> DataResult.success(vMovieMapper.map(it.data!!))
                Status.ERROR -> DataResult.error(it.error!!, it.data?.let{ vMovieMapper.map(it) })
                Status.LOADING -> DataResult.loading(it.data?.let{ vMovieMapper.map(it) })
            }
        }
}