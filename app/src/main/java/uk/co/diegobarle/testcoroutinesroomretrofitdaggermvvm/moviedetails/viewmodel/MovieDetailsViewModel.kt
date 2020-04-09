package uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import uk.co.diegobarle.core.datamanager.DataResult
import uk.co.diegobarle.core.datamanager.RequestLevel
import uk.co.diegobarle.core.datamanager.Status
import uk.co.diegobarle.core.repository.MovieRepository
import uk.co.diegobarle.testcoroutinesroomretrofitdaggermvvm.moviedetails.model.VMovieDetailsMapper
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val moviesRepository: MovieRepository,
    private val vMovieDetailsMapper: VMovieDetailsMapper
): ViewModel(){

    fun getMovie(id: Int) =
        moviesRepository.getMovie(id, RequestLevel.DB_ONLY).map {
            when(it.status){
                Status.SUCCESS -> DataResult.success(vMovieDetailsMapper.map(it.data!!))
                Status.ERROR -> DataResult.error(it.error!!, it.data?.let{ vMovieDetailsMapper.map(it) })
                Status.LOADING -> DataResult.loading(it.data?.let{ vMovieDetailsMapper.map(it) })
            }
        }
}